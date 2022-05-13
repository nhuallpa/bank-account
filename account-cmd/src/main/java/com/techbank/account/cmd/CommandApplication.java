package com.techbank.account.cmd;

import com.techbank.account.cmd.api.commands.*;
import com.techbank.cqrs.core.infrastructure.CommandDispacher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import javax.annotation.PostConstruct;

@SpringBootApplication
public class CommandApplication {

	@Autowired
	private CommandDispacher commandDispacher;

	@Autowired
	private CommandHandler commandHandler;


	public static void main(String[] args) {
		SpringApplication.run(CommandApplication.class, args);
	}

	@PostConstruct
	public void registerHandlers() {
		commandDispacher.registerHandler(OpenAccountCommand.class, commandHandler::handle);
		commandDispacher.registerHandler(DepositFudsCommand.class, commandHandler::handle);
		commandDispacher.registerHandler(WithdrawFundsCommand.class, commandHandler::handle);
		commandDispacher.registerHandler(CloseAccountCommand.class, commandHandler::handle);
	}

}
