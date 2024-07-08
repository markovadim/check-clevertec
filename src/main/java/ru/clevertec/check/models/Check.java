package main.java.ru.clevertec.check.models;

import java.time.LocalDateTime;

public class Check {

    private Order order;
    private LocalDateTime localDateTime;

    private String exceptionMessage;

    public Check(Builder builder) {
        this.order = builder.order;
        this.localDateTime = builder.localDateTime;
    }

    public Check(String exceptionMessage) {
        this.exceptionMessage = exceptionMessage;
    }

    public Order getOrder() {
        return order;
    }

    public LocalDateTime getLocalDateTime() {
        return localDateTime;
    }

    public String getExceptionMessage() {
        return exceptionMessage;
    }

    public static class Builder {
        private Order order;
        private LocalDateTime localDateTime;

        public Builder order(Order order) {
            this.order = order;
            return this;
        }

        public Builder localDateTime(LocalDateTime localDateTime) {
            this.localDateTime = localDateTime;
            return this;
        }

        public Check build() {
            return new Check(this);
        }
    }
}
