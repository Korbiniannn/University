# Evolutionary Computation 

## Project setup 

If you are not already using [uv]() on your system, then install `uv` (on Linux/macOS/WSL):

```sh
curl -LsSf https://astral.sh/uv/install.sh | sh
```

Read at least the [Getting Started](https://docs.astral.sh/uv/getting-started/) section. Move into the project directory and run 

```
uv sync 
```

To view and edit the Jupyter notebooks you can use [VS Code](https://code.visualstudio.com/) or use/start a Jupyter server with uv using 

```sh
uv run --with jupyter jupyter lab
```

By default, `jupyter lab` will start the server at [http://localhost:8888/lab](http://localhost:8888/lab). For additional information on using Jupyter lab with `uv`, see [https://docs.astral.sh/uv/guides/integration/jupyter/](https://docs.astral.sh/uv/guides/integration/jupyter/). 



