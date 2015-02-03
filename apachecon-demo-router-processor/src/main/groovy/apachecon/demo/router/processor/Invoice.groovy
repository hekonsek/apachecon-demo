package apachecon.demo.router.processor

class Invoice {

    private String invoiceId;

    private long netValue;

    Invoice() {
    }

    Invoice(String invoiceId, long netValue) {
        this.invoiceId = invoiceId;
        this.netValue = netValue;
    }

    String getInvoiceId() {
        return invoiceId;
    }

    void setInvoiceId(String invoiceId) {
        this.invoiceId = invoiceId;
    }

    long getNetValue() {
        return netValue;
    }

    void setNetValue(long netValue) {
        this.netValue = netValue;
    }

}
