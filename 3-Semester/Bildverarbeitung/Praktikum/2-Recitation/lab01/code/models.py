#!/usr/bin/python3

"""
PyTorch tutorial on constructing neural networks:
https://pytorch.org/tutorials/beginner/blitz/neural_networks_tutorial.html
"""
import numpy as np
import torch
import torch.nn as nn
import torch.nn.functional as F

from part1 import create_Gaussian_kernel


class HybridImageModel(nn.Module):
  def __init__(self):
    """
    Initializes an instance of the HybridImageModel class.
    """
    super(HybridImageModel, self).__init__()

  def get_kernel(self, cutoff_frequency: int) -> torch.Tensor:
    """
    Returns a Gaussian kernel using the specified cutoff frequency.

    PyTorch requires the kernel to be of a particular shape in order to apply
    it to an image. Specifically, the kernel needs to be of shape (c, 1, k, k)
    where c is the # channels in the image. Start by getting a 2D Gaussian
    kernel using your implementation from Part 1, which will be of shape
    (k, k). Then, let's say you have an RGB image, you will need to turn this
    into a Tensor of shape (3, 1, k, k) by stacking the Gaussian kernel 3
    times.

    Args
    - cutoff_frequency: int specifying cutoff_frequency
    Returns
    - kernel: Tensor of shape (c, 1, k, k) where c is # channels

    HINTS:
    - You will use the create_Gaussian_kernel() function from part1.py in this
      function.
    - Since the # channels may differ across each image in the dataset, make
      sure you don't hardcode the dimensions you reshape the kernel to. There
      is a variable defined in this class to give you channel information.
    - You can use np.reshape() to change the dimensions of a numpy array.
    - You can use np.tile() to repeat a numpy array along specified axes.
    - You can use torch.Tensor() to convert numpy arrays to torch Tensors.
    """

    ############################
    ### TODO: YOUR CODE HERE ###

    gaussian_kernel = create_Gaussian_kernel(cutoff_frequency)  # numpy array of shape (k, k)

    # Umwandlung in 4D-Form mit np.reshape()
    k = gaussian_kernel.shape[0]
    gaussian_kernel = np.reshape(gaussian_kernel, (1, 1, k, k))  # (1, 1, k, k)

    # Wiederhole den Kernel für alle Kanäle
    gaussian_kernel = np.tile(gaussian_kernel, (self.n_channels, 1, 1, 1))  # (c, 1, k, k)
    
    kernel = torch.tensor(gaussian_kernel, dtype=torch.foat32)

    ### END OF STUDENT CODE ####
    ############################

    return kernel

  def low_pass(self, x, kernel):
    """
    Applies low pass filter to the input image.

    Args:
    - x: Tensor of shape (b, c, m, n) where b is batch size
    - kernel: low pass filter to be applied to the image
    Returns:
    - filtered_image: Tensor of shape (b, c, m, n)

    HINT:
    - You should use the 2d convolution operator from torch.nn.functional.
    - Make sure to pad the image appropriately (it's a parameter to the
      convolution function you should use here!).
    - Pass self.n_channels as the value to the "groups" parameter of the
      convolution function. This represents the # of channels that the filter
      will be applied to.
    """

    ############################
    ### TODO: YOUR CODE HERE ###

    if kernel.ndim != 2:
        raise ValueError("Kernel must be a 2D tensor")

    # Kernel auf die richtige Form bringen: (out_channels, in_channels/groups, kH, kW)
    kernel = kernel.unsqueeze(0).unsqueeze(0)  # Form: (1, 1, kH, kW)
    kernel = kernel.repeat(self.n_channels, 1, 1, 1)  # Form: (c, 1, kH, kW)

    # Padding berechnen
    pad_h = kernel.shape[2] // 2  # Höhe des Kernels
    pad_w = kernel.shape[3] // 2  # Breite des Kernels
    padding = (pad_w, pad_w, pad_h, pad_h)  # (links, rechts, oben, unten)

    # Convolution anwenden
    filtered_image = F.conv2d(
        x, kernel, padding=padding, groups=self.n_channels
    )

    ### END OF STUDENT CODE ####
    ############################

    return filtered_image

  def forward(self, image1, image2, cutoff_frequency):
    """
    Takes two images and creates a hybrid image. Returns the low frequency
    content of image1, the high frequency content of image 2, and the hybrid
    image.

    Args
    - image1: Tensor of shape (b, c, m, n)
    - image2: Tensor of shape (b, c, m, n)
    - cutoff_frequency: Tensor of shape (b)
    Returns:
    - low_frequencies: Tensor of shape (b, c, m, n)
    - high_frequencies: Tensor of shape (b, c, m, n)
    - hybrid_image: Tensor of shape (b, c, m, n)

    HINTS:
    - You will use the get_kernel() function and your low_pass() function in
      this function.
    - Similar to Part 1, you can get just the high frequency content of an
      image by removing its low frequency content.
    - Don't forget to make sure to clip the pixel values >=0 and <=1. You can
      use torch.clamp().
    - If you want to use images with different dimensions, you should resize
      them in the HybridImageDataset class using torchvision.transforms.
    """
    self.n_channels = image1.shape[1]

    ############################
    ### TODO: YOUR CODE HERE ###

    # Erzeuge den Low-Pass-Filter für jede Cutoff-Frequenz
    kernel = self.get_kernel(cutoff_frequency)

    if isinstance(kernel, np.ndarray):
      kernel = torch.tensor(kernel, dtype=torch.float32)

    # Low-Frequencies von image1
    low_frequencies = self.low_pass(image1, kernel)

    # Low-Frequencies von image2
    low_frequencies_image2 = self.low_pass(image2, kernel)

    # High-Frequencies von image2
    high_frequencies = image2 - low_frequencies_image2

    # Hybrid-Bild erstellen
    hybrid_image = low_frequencies + high_frequencies

    # Clipping, um Werte zwischen 0 und 1 zu begrenzen
    hybrid_image = torch.clamp(hybrid_image, 0, 1)


    ### END OF STUDENT CODE ####
    ############################

    return low_frequencies, high_frequencies, hybrid_image
