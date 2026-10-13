import java.util.ArrayList;
import java.util.List;

class Product{
	
	String name;
	int price;
	
	public Product(String name, int price) {
		super();
		this.name = name;
		this.price = price;
	}

}

class ShoppingCart{
	
	List<Product> productList=new ArrayList<Product>();
	
	public void addProduvt(Product p) {
		productList.add(p);
	}
	
	public List<Product> getProduct(){
		return this.productList;
	}
	
	public int calculateTotalPrice() {
		
		int sum=0;
		for(Product p: productList) {
			sum+=p.price;
		}
		
		return sum;
	}
}

interface Discount {
	
	int calculateDiscount(int total);
    
}

class NormalDiscount implements Discount{

	@Override
	public int calculateDiscount(int total) {
		System.out.println("NormalDiscount.calculateDiscount()");
		return total * 10 / 100;
	}
	
}

class PremiumDiscount implements Discount{

	@Override
	public int calculateDiscount(int total) {
		System.out.println("PremiumDiscount.calculateDiscount()");
		return total * 20 / 100;
	}
	
}

class VIPDiscount implements Discount{

	@Override
	public int calculateDiscount(int total) {
		System.out.println("VIPDiscount.calculateDiscount()");
		return total*30/100;
	}
	
}


class DisCountSelector{
	
	public Discount getDiscount(int total) {
		
		if(total>=3000) {
			return new VIPDiscount();
		}else if(total>=2000 && total<3000) {
			return new PremiumDiscount();
		}else {
			return new NormalDiscount();
		}
	}


}


class ShoppingCartPrinter{
	
	private ShoppingCart cart;
	
	
	public ShoppingCartPrinter(ShoppingCart c) {
		this.cart=c;
	}
	
	public void printInvoice() {
		System.out.println("Shopping Cart Invoice:");
		for(Product p: cart.getProduct()) {
			System.out.println(p.name+":- "+p.price);
		}
		
		DisCountSelector countSelector=new DisCountSelector();
		
		int total = cart.calculateTotalPrice();
        Discount discount = countSelector.getDiscount(total);
        int dis=discount.calculateDiscount(total);
        int finalPrice = total - dis;

        System.out.println("Shopping Cart Invoice:");
        System.out.println("Total Bill: " + total);
        System.out.println("Discount: " + dis);
        System.out.println("Final Bill: " + finalPrice);
	}
}

interface Persistence{
	
	void save();
}

class SQLPersistence implements Persistence{

	@Override
	public void save() {
		System.out.println("SQLPersistence.save()");
		
	}
	
}


class MongoDBPersistence implements Persistence{

	@Override
	public void save() {
		System.out.println("MongoDBPersistence.save()");
		
	}
	
}

class FilePersistence implements Persistence{

	@Override
	public void save() {
		System.out.println("FilePersistence.save()");
		
	}
	
}

public class OpenCloseFollowed {
    public static void main(String[] args) {

        ShoppingCart cart=new ShoppingCart();
		cart.addProduvt(new Product("Mouse", 600));
		cart.addProduvt(new Product("Jeans", 1450));
		cart.addProduvt(new Product("TV Remote", 1000));
		
		ShoppingCartPrinter printer=new ShoppingCartPrinter(cart);
		printer.printInvoice();

        Persistence sql=new SQLPersistence();
        sql.save();
        
    }
}
