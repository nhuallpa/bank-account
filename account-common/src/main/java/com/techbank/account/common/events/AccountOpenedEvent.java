package com.techbank.account.common.events;

import com.techbank.account.common.dto.AccountType;
import com.techbank.cqrs.core.events.BaseEvent;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.experimental.SuperBuilder;

import java.util.Date;

@AllArgsConstructor
@Data
@SuperBuilder
public class AccountOpenedEvent extends BaseEvent {
    private String accountHolder;
    private AccountType accountType;
    private Date createdDate;
    private double openingBalance;


}
