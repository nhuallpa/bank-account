package com.techbank.account.cmd.api.commands;

import com.techbank.account.cmd.domain.AccountAggregate;
import com.techbank.cqrs.core.handlers.EventSourcingHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AccountCommandHandler implements CommandHandler {

    @Autowired
    private EventSourcingHandler<AccountAggregate> eventSourcingHandler;

    @Override
    public void handle(OpenAccountCommand command) {
        var accountAggregate = new AccountAggregate(command);
        eventSourcingHandler.save(accountAggregate);
    }

    @Override
    public void handle(DepositFudsCommand command) {
        var accountAggregate = eventSourcingHandler.getById(command.getId());
        accountAggregate.depositFunds(command.getAmount());
        eventSourcingHandler.save(accountAggregate);
    }

    @Override
    public void handle(WithdrawFundsCommand command) {
        var accountAggregate = eventSourcingHandler.getById(command.getId());
        if (command.getAmount() > accountAggregate.getBalance()) {
            throw new IllegalStateException("Withdrawal declined, insuffient funds!");
        }
        accountAggregate.withdrawFunds(command.getAmount());
        eventSourcingHandler.save(accountAggregate);
    }

    @Override
    public void handle(CloseAccountCommand command) {
        var accountAggregate = eventSourcingHandler.getById(command.getId());
        accountAggregate.closeAccount();
        eventSourcingHandler.save(accountAggregate);
    }
}
