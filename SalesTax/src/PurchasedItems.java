import java.math.BigDecimal;

/**
 * file: PurchasedItems.java
 * 
 * author: Patrick McDonough
 * 
 * brief: Calculates the tax, intializes variables, and
 * includes getters and setters needed in Receipt.  
 */

public class PurchasedItems implements TaxCalculator {
	/**
	 * Intitalizes quantity, tax, and price. Also, tax rates and rounding rate 
	 * for the sales tax.
	 */
	private int quantity = 0;
	private String item = null;
	private float price = 0.0f;
	private float tax = 0.0f;
	private boolean imported = false;
	private boolean exempt = false;
	private float itemPriceIncludingTax = 0.0f;
	private static final float BASIC_TAX_PERCENT = 0.10f;
	private static final float IMPORT_TAX_PERCENT = 0.05f;
	private static final float ROUND_RATE = 0.05f;   

	public String toString() {
		return getQuantity() + " " + getItem() + ": " 
				+ getItemPriceIncludingTax();
	}

	/**
	 * Calculates the tax percent based on if the item is imported/exempted 
	 */
	public void calculateTax() {

		float totalTaxPercent = 0.0f;

		if (isImported() && isExempt()) {
			totalTaxPercent = IMPORT_TAX_PERCENT;
		}

		else if (isImported() && !isExempt()) {
			totalTaxPercent = BASIC_TAX_PERCENT + IMPORT_TAX_PERCENT;
		}

		else if (!isImported() && !isExempt()) {
			totalTaxPercent = BASIC_TAX_PERCENT;
		}

		tax = round(totalTaxPercent * price);
	}

	/**
	 * Implements the rounding rules for the sales tax and returns the sales 
	 * tax after the round.
	 */

	public float round(float taxBeforeRound) {
		float taxAfterRound =  ((float)(Math.ceil(taxBeforeRound 
				/ IMPORT_TAX_PERCENT) * IMPORT_TAX_PERCENT));
		BigDecimal bigDecimalTax = new BigDecimal( taxAfterRound );
		bigDecimalTax = bigDecimalTax.setScale( 2, BigDecimal.ROUND_HALF_UP );
		taxAfterRound=bigDecimalTax.floatValue();
		return taxAfterRound;
	}

	/**
	 * Getters and setters
	 */

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public String getItem() {
		return item;
	}

	public void setItem(String item) {
		this.item = item;
	}

	public float getPrice() {
		return price;
	}

	public void setPrice(float price) {
		this.price = price;
	}

	public float getTax() {
		return tax;
	}

	public void setTax(float tax) {
		this.tax = tax;
	}

	public boolean isImported() {
		return imported;
	}

	public void setImported(boolean imported) {
		this.imported = imported;
	}

	public boolean isExempt() {
		return exempt;
	}

	public void setExempt(boolean exempt) {
		this.exempt = exempt;
	}

	public float getItemPriceIncludingTax() {
		return itemPriceIncludingTax;
	}

	public void setItemPriceIncludingTax(float itemPriceIncludingTax) {
		this.itemPriceIncludingTax = itemPriceIncludingTax;
	}



}