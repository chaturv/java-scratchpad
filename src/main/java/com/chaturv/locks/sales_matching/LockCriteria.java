package com.chaturv.locks.sales_matching;

import java.math.BigDecimal;

public class LockCriteria {

    private String trader;
    private BigDecimal notional;

    public LockCriteria(String trader, BigDecimal notional) {
        this.trader = trader;
        this.notional = notional;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        LockCriteria that = (LockCriteria) o;

        return notional.compareTo(that.notional) == 0 && trader.equals(that.trader);

    }

    @Override
    public int hashCode() {
        int result = trader.hashCode();
        result = 31 * result + notional.hashCode();
        return result;
    }
}
