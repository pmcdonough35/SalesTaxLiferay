import java.util.*;

/**
 * file: Receipt.java
 * 
 * author: Patrick McDonough
 * 
 * brief: Prints the receipt containing the purchased items
 */

public class Receipt {

	/**
	 * Gets purchase and prints receipt. Also checks for incorrect
	 * inputs and outputs error message.
	 */
	public static void main(String [] args) {

		Receipt receipt = new Receipt();

		try
		{
			List<PurchasedItems> basket = receipt.getPurchase();
			receipt.printReceipt(basket);
		}
		catch (NumberFormatException numberException)
		{
			numberException.printStackTrace();
		}
		catch (Exception exception)
		{
			exception.printStackTrace();
		}
	}

	/**
	 * Prints the individual and total price of all of the items with 
	 * tax. Also prints the sales and total tax.
	 */

	public void printReceipt (List<PurchasedItems> allItems) {
		float totalPrice = 0.0f;
		float totalTax = 0.0f;
		float priceWithTax = 0.0f;

		for (int i = 0; i < allItems.size(); i++) {      
			PurchasedItems purchaseItem = allItems.get(i);
			purchaseItem = allItems.get(i);
			totalPrice += purchaseItem.getPrice();
			totalTax += purchaseItem.getTax();
			purchaseItem.setItemPriceIncludingTax(purchaseItem.getPrice() 
					+ purchaseItem.getTax());
			System.out.println(purchaseItem);
			priceWithTax = priceWithTax 
					+ purchaseItem.getItemPriceIncludingTax();

		}

		System.out.println("Sales Tax: " + totalTax);
		System.out.println("Total: " + (priceWithTax));

	}

	/**
	 * Creates an ArrayList for purchased items
	 */

	private List<PurchasedItems> getPurchase() {
		Scanner scan = new Scanner(System.in);
		String input = null;

		List<PurchasedItems> allItems = new ArrayList<PurchasedItems>();

		int totalItems = 1;
		while(true) {
			PurchasedItems purchaseItem = new PurchasedItems();

			System.out.print("Item " + totalItems + " Quantity: ");
			input = scan.nextLine(); 
			purchaseItem.setQuantity(Integer.parseInt(input));

			System.out.println("Item " + totalItems + " Name: ");
			input = scan.nextLine();
			purchaseItem.setItem(input);

			System.out.println("Item " + totalItems + " Price: ");
			input = scan.nextLine();
			purchaseItem.setPrice(Float.parseFloat(input));

			System.out.println("Is Item " + totalItems + " Imported[y/n]: ");
			input = scan.nextLine();
			if (input.toLowerCase().equals("y")) {
				purchaseItem.setImported(true);              
			}

			System.out.println("Is Item " + totalItems 
					+ " Exempt[y/n] (books, food, and medical products): ");
			input = scan.nextLine();
			if (input.toLowerCase().equals("y")) {
				purchaseItem.setExempt(true);              
			}

			purchaseItem.calculateTax();

			allItems.add(purchaseItem);
			totalItems++;

			System.out.print("More Items [y/n]?");
			input = scan.nextLine();

			if (input.toLowerCase().equals("n")) {
				break;

			}
		}

			return allItems;

		}
	}

	
