package apachecon.demo.router.inendpoint;

public class Invoice {

    private String invoiceId;

    private long netValue;

    Invoice() {
    }

    Invoice(String invoiceId, long netValue) {
        this.invoiceId = invoiceId;
        this.netValue = netValue;
    }

    public String getInvoiceId() {
        return invoiceId;
    }

    public void setInvoiceId(String invoiceId) {
        this.invoiceId = invoiceId;
    }

    public long getNetValue() {
        return netValue;
    }

    public void setNetValue(long netValue) {
        this.netValue = netValue;
    }

}
