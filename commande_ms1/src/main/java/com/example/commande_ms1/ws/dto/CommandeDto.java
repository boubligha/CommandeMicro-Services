package com.example.commande_ms1.ws.dto;

import java.math.BigDecimal;

public class CommandeDto {
        private int id;
        private String ref;
        private BigDecimal total;
        private BigDecimal totalPaye;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getRef() {
            return ref;
        }

        public void setRef(String ref) {
            this.ref = ref;
        }

        public BigDecimal getTotal() {
            return total;
        }

        public void setTotal(BigDecimal total) {
            this.total = total;
        }

        public BigDecimal getTotalPaye() {
            return totalPaye;
        }

        public void setTotalPaye(BigDecimal totalPaye) {
            this.totalPaye = totalPaye;
        }
}

