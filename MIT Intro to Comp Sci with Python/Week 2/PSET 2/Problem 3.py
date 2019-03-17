def min_payment(balance, annualInterestRate):
    lower_payment = balance / 12
    upper_payment = (balance * (1 + annualInterestRate)**12) / 12
    end_balance = balance
    while end_balance > 0:
        end_balance = balance
        payment = (lower_payment + upper_payment / 2)
        for i in range(12):
            end_balance = (end_balance - payment) * (1 + (annualInterestRate/12))
    print("Lowest Payment: {}".format(payment))

min_payment(3926, .2)
