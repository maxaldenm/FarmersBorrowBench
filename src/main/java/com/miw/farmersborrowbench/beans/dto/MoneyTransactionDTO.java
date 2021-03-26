package com.miw.farmersborrowbench.beans.dto;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Locale;

public class MoneyTransactionDTO implements Comparable<MoneyTransactionDTO> {

    private double amount;

    private String description;

    private String senderAccount;

    private String receiverAccount;

    private LocalDateTime date;

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getSenderAccount() {
        return senderAccount;
    }

    public void setSenderAccount(String senderAccount) {
        this.senderAccount = senderAccount;
    }

    public String getReceiverAccount() {
        return receiverAccount;
    }

    public void setReceiverAccount(String receiverAccount) {
        this.receiverAccount = receiverAccount;
    }

    public LocalDateTime getDate() {
        return date;
    }

    public void setDate(LocalDateTime date) {
        this.date = date;
    }

    public String amountWithoutDecimalToString() {
        String amountString = String.valueOf(this.amount);
        String[] parts = amountString.split("\\.");
        return parts[0];
    }

    public String amountOnlyDecimalPlacesToString() {
        String amountString = String.valueOf(this.amount);
        String[] parts = amountString.split("\\.");
        if (parts[1].isEmpty()) {
            return "00";
        } else if (parts[1].length() == 1) {
            return parts[1] + "0";
        } else
            return parts[1].substring(0, 2);
    }

    public String localDateTimeToDateWithSlash() {
        return DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm").format(this.date);
    }

    @Override
    public String toString() {
        return "MoneyTransactionDTO{" +
                "amount=" + amount +
                ", description='" + description + '\'' +
                ", senderAccount='" + senderAccount + '\'' +
                ", receiverAccount='" + receiverAccount + '\'' +
                ", date=" + date +
                '}';
    }

    public String toSearchString() {
        return date.toString()+" "+ senderAccount +" "+ receiverAccount +" "+description+" "+amount;
    }

    @Override
    public int compareTo(MoneyTransactionDTO o) {
        return getDate().compareTo(o.getDate());
    }

    public static enum Comparators implements Comparator<MoneyTransactionDTO> {
        BY_DATE {
            public int compare(MoneyTransactionDTO o1, MoneyTransactionDTO o2) {
                return o1.getDate().compareTo(o2.getDate());
            }

            public void sort(final List<MoneyTransactionDTO> l) {
                Collections.sort(l, BY_DATE);
            }
        },
        BY_DATE_REVERSED {
            public int compare(MoneyTransactionDTO o1, MoneyTransactionDTO o2) {
                return (o1.getDate().compareTo(o2.getDate()))*-1;
            }

            public void sort(final List<MoneyTransactionDTO> l) {
                Collections.sort(l, BY_DATE_REVERSED);
            }
        },
        BY_ACCOUNT {
            public int compare(MoneyTransactionDTO o1, MoneyTransactionDTO o2) {
                return (o1.getReceiverAccount().compareTo(o2.getReceiverAccount()));
            }

            public void sort(final List<MoneyTransactionDTO> l) {
                Collections.sort(l, BY_ACCOUNT);
            }
        },
        BY_DESCRIPTION {
            public int compare(MoneyTransactionDTO o1, MoneyTransactionDTO o2) {
                return o1.getDescription().toLowerCase(Locale.ROOT).compareTo(o2.getDescription().toLowerCase(Locale.ROOT));
            }

            public void sort(final List<MoneyTransactionDTO> l) {
                Collections.sort(l, BY_DESCRIPTION);
            }
        },
        BY_DESCRIPTION_REVERSED {
            public int compare(MoneyTransactionDTO o1, MoneyTransactionDTO o2) {
                return (o1.getDescription().toLowerCase(Locale.ROOT).compareTo(o2.getDescription().toLowerCase(Locale.ROOT)))*-1;
            }

            public void sort(final List<MoneyTransactionDTO> l) {
                Collections.sort(l, BY_DESCRIPTION_REVERSED);
            }
        },
        BY_AMOUNT {
            public int compare(MoneyTransactionDTO o1, MoneyTransactionDTO o2) {
                return (int) (o1.getAmount() - o2.getAmount());
            }

            public void sort(final List<MoneyTransactionDTO> l) {
                Collections.sort(l, BY_AMOUNT);
            }
        },
        BY_AMOUNT_REVERSED {
            public int compare(MoneyTransactionDTO o1, MoneyTransactionDTO o2) {
                return ((int)(o1.getAmount() - o2.getAmount()))*-1;
            }

            public void sort(final List<MoneyTransactionDTO> l) {
                Collections.sort(l, BY_AMOUNT_REVERSED);
            }
        };

        public abstract void sort(List<MoneyTransactionDTO> l);
    }
}
