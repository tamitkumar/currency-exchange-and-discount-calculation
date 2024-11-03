package com.currency.exch.discount.calc.services;

import com.currency.exch.discount.calc.model.BillDetails;
import com.currency.exch.discount.calc.model.GreetingMessage;

public interface ExchangeRateService {

	public GreetingMessage calculatePayableAmount(BillDetails billDetails);
}
