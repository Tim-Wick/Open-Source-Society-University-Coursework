def balance(balance, annualInterestRate, monthlyPaymentRate, months):
    for i in range(months):
        min_payment = balance*monthlyPaymentRate
        balance -= min_payment
        balance *= 1 + (annualInterestRate/12)
        round(balance, 2)
        print("Month {} remaining balance is {}".format(i+1, balance))
    return balance

print(balance(42, .2, .04, 12))
print(balance(484, .2, .04, 12))
