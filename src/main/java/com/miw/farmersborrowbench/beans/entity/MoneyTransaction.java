package com.miw.farmersborrowbench.beans.entity;

import com.miw.farmersborrowbench.beans.entity.Account;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Locale;

@Entity
public class MoneyTransaction implements Comparable<MoneyTransaction>{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private double amount;

    private String description;

    @ManyToOne
    private Account debitAccount;

    @ManyToOne
    private Account creditAccount;

    private LocalDateTime date;

    public MoneyTransaction() {
    }

    public MoneyTransaction(double amount, String description, Account debitAccount, Account creditAccount, LocalDateTime date) {
        this.amount = amount;
        this.description = description;
        this.debitAccount = debitAccount;
        this.creditAccount = creditAccount;
        this.date = date;
    }

    public int getId() {
        return id;
    }

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

    public Account getDebitAccount() {
        return debitAccount;
    }

    public void setDebitAccount(Account debitAccount) {
        this.debitAccount = debitAccount;
    }

    public Account getCreditAccount() {
        return creditAccount;
    }

    public void setCreditAccount(Account creditAccount) {
        this.creditAccount = creditAccount;
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
        return DateTimeFormatter.ofPattern("dd/MM/yyyy").format(this.date);
    }

    @Override
    public String toString() {
        return "MoneyTransaction{" +
                "amount=" + amount +
                ", description='" + description + '\'' +
                ", debitAccount=" + debitAccount.toString() +
                ", creditAccount=" + creditAccount.toString() +
                ", date=" + date +
                '}';
    }

    public String toSearchString() {
        return date.toString()+" "+debitAccount.getAccountNumber()+" "+creditAccount.getAccountNumber()+" "+description+" "+amount;
    }

    @Override
    public int compareTo(MoneyTransaction o) {
        return getDate().compareTo(o.getDate());
    }

    public static enum Comparators implements Comparator<MoneyTransaction> {
        BY_DATE {
            public int compare(MoneyTransaction o1, MoneyTransaction o2) {
                return o1.getDate().compareTo(o2.getDate());
            }

            public void sort(final List<MoneyTransaction> l) {
                Collections.sort(l, BY_DATE);
            }
        },
        BY_DATE_REVERSED {
            public int compare(MoneyTransaction o1, MoneyTransaction o2) {
                return (o1.getDate().compareTo(o2.getDate()))*-1;
            }

            public void sort(final List<MoneyTransaction> l) {
                Collections.sort(l, BY_DATE_REVERSED);
            }
        },
        BY_ACCOUNT {
            public int compare(MoneyTransaction o1, MoneyTransaction o2) {
                return (o1.getCreditAccount().getAccountNumber().compareTo(o2.getCreditAccount().getAccountNumber()));
            }

            public void sort(final List<MoneyTransaction> l) {
                Collections.sort(l, BY_ACCOUNT);
            }
        },
        BY_DESCRIPTION {
            public int compare(MoneyTransaction o1, MoneyTransaction o2) {
                return o1.getDescription().toLowerCase(Locale.ROOT).compareTo(o2.getDescription().toLowerCase(Locale.ROOT));
            }

            public void sort(final List<MoneyTransaction> l) {
                Collections.sort(l, BY_DESCRIPTION);
            }
        },
        BY_DESCRIPTION_REVERSED {
            public int compare(MoneyTransaction o1, MoneyTransaction o2) {
                return (o1.getDescription().toLowerCase(Locale.ROOT).compareTo(o2.getDescription().toLowerCase(Locale.ROOT)))*-1;
            }

            public void sort(final List<MoneyTransaction> l) {
                Collections.sort(l, BY_DESCRIPTION_REVERSED);
            }
        },
        BY_AMOUNT {
            public int compare(MoneyTransaction o1, MoneyTransaction o2) {
                return (int) (o1.getAmount() - o2.getAmount());
            }

            public void sort(final List<MoneyTransaction> l) {
                Collections.sort(l, BY_AMOUNT);
            }
        },
        BY_AMOUNT_REVERSED {
            public int compare(MoneyTransaction o1, MoneyTransaction o2) {
                return ((int)(o1.getAmount() - o2.getAmount()))*-1;
            }

            public void sort(final List<MoneyTransaction> l) {
                Collections.sort(l, BY_AMOUNT_REVERSED);
            }
        };

        public abstract void sort(List<MoneyTransaction> l);
    }
}

