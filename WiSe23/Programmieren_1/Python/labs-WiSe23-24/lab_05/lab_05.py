class VendingMachine:
    """A vending machine that vends some product for some price.
    >>> v = VendingMachine('candy', 10)
    >>> v.vend()
    'Nothing left to vend. Please restock.'
    >>> v.add_funds(15)
    'Nothing left to vend. Please restock. Here is your $15.'
    >>> v.restock(2)
    'Current candy stock: 2'
    >>> v.vend()
    'Please add $10 more funds.'
    >>> v.add_funds(7)
    'Current balance: $7'
    >>> v.vend()
    'Please add $3 more funds.'
    >>> v.add_funds(5)
    'Current balance: $12'
    >>> v.vend()
    'Here is your candy and $2 change.'
    >>> v.add_funds(10)
    'Current balance: $10'
    >>> v.vend()
    'Here is your candy.'
    >>> v.add_funds(15)
    'Nothing left to vend. Please restock. Here is your $15.'
    >>> w = VendingMachine('soda', 2)
    >>> w.restock(3)
    'Current soda stock: 3'
    >>> w.restock(3)
    'Current soda stock: 6'
    >>> w.add_funds(2)
    'Current balance: $2'
    >>> w.vend()
    'Here is your soda.'
    """
    # YOUR CODE HERE
    balance = 0
    stock = 0
    def __init__(self, item, item_price):
        self.item_price = item_price
        self.item = item
    
    def vend(self):
        if self.stock == 0:
            return ('Nothing left to vend. Please restock.')
        else:
            if self.balance < self.item_price:
                money_left = self.item_price - self.balance
                answer = (f'Please add ${money_left} more funds.')
                return answer
            elif self.balance == self.item_price:
                self.stock -= 1
                self.balance = 0
                return (f'Here is your {self.item}.')
            elif self.balance > self.item_price:
                change = self.balance - self.item_price
                self.balance = 0
                self.stock -= 1
                return (f'Here is your {self.item} and ${change} change.')
            
    def add_funds(self, amount):
        self.balance += amount
        if self.stock == 0:
            tmp_balance = self.balance
            self.balance = 0 
            return (f'Nothing left to vend. Please restock. Here is your ${tmp_balance}.') 
        return (f'Current balance: ${self.balance}')
    
    def restock(self, item_amount):
        self.stock += item_amount
        return (f'Current {self.item} stock: {self.stock}')
        



