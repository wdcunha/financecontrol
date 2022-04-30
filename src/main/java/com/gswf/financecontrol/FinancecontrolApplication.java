package com.gswf.financecontrol;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import com.gswf.financecontrol.model.PaymentTypes;
import com.gswf.financecontrol.model.Person;
import com.gswf.financecontrol.model.PersonTypes;
import com.gswf.financecontrol.model.Product;
import com.gswf.financecontrol.model.PurchasePayment;
import com.gswf.financecontrol.model.PurchaseProduct;
import com.gswf.financecontrol.model.Purchases;
import com.gswf.financecontrol.model.Sales;
import com.gswf.financecontrol.service.PaymentTypesService;
import com.gswf.financecontrol.service.PersonService;
import com.gswf.financecontrol.service.PersonTypesService;
import com.gswf.financecontrol.service.ProductService;
import com.gswf.financecontrol.service.PurchasePaymentService;
import com.gswf.financecontrol.service.PurchaseProductService;
import com.gswf.financecontrol.service.PurchasesService;
import com.gswf.financecontrol.service.SalesService;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class FinancecontrolApplication {

	public static void main(String[] args) {
		SpringApplication.run(FinancecontrolApplication.class, args);
	}

	@Bean
	CommandLineRunner initDataBase(PersonTypesService personTypesService, PaymentTypesService paymentTypesService, 
									PersonService personService, ProductService productService, PurchaseProductService purchaseProductService,
									PurchasesService purchasesService, PurchasePaymentService purchasePaymentService, SalesService salesService) {
		return args -> {
			salesService.deleteAllSales();
			purchasesService.deleteAllPurchases();
			productService.deleteAllProducts();
			personService.deleteAllPeople();
			personTypesService.deleteAllPersonTypes();
			purchaseProductService.deleteAllPurchaseProduct();
			purchasePaymentService.deleteAllPurchasePayment();
			paymentTypesService.deleteAllPaymentTypes();

			Product product = new Product();
			product.setDescription("Moletin branco");
			product.setSize("m");
			product.setPrice(30.03);
			product.setQuantity(15);
			product.setNotes("produto em promoção");

			Product product2 = new Product();
			product2.setDescription("Blusinha branca");
			product2.setSize("gg");
			product2.setPrice(10.01);
			product2.setQuantity(10);
			product2.setNotes("qualquer coisa pode colocar aqui");

			Product product3 = new Product();
			product3.setDescription("Blusinha preta");
			product3.setSize("p");
			product3.setPrice(14.14);
			product3.setQuantity(15);
			product3.setNotes("independente, cada produto tem o seu campo de observação");

			Product product4 = new Product();
			product4.setDescription("Calça jeans rosa");
			product4.setSize("g");
			product4.setPrice(23.23);
			product4.setQuantity(13);
			product4.setNotes("como pode ver aqui, é diferente");

			Product product5 = new Product();
			product5.setDescription("Jaqueta de couro");
			product5.setSize("g");
			product5.setPrice(18.18);
			product5.setQuantity(13);
			product5.setNotes("como pode ver aqui, é diferente");
			
			List<Product> productsForStock = new ArrayList<>();
			productsForStock.add(product);
			productsForStock.add(product2);
			productsForStock.add(product3);
			productsForStock.add(product4);
			productsForStock.add(product5);
			
			productService.saveAllProduct(productsForStock);

			Product productForSales = new Product();
			productForSales.setDescription("Vestido da hora");
			productForSales.setSize("g");
			productForSales.setPrice(100.00);
			productForSales.setQuantity(103);
			productForSales.setNotes("pra vendas");

			Product productForPurchase = new Product();
			productForPurchase.setDescription("Macacão em tecido de linho");
			productForPurchase.setSize("m");
			productForPurchase.setPrice(200.00);
			productForPurchase.setQuantity(135);
			productForPurchase.setNotes("pra compras");

			Product productForPurchase2 = new Product();
			productForPurchase2.setDescription("Macaquito em material de lycra");
			productForPurchase2.setSize("p");
			productForPurchase2.setPrice(230.00);
			productForPurchase2.setQuantity(17);
			productForPurchase2.setNotes("testa many to many");
						
			List<Product> productsForAll = new ArrayList<>();
			productsForAll.add(productForSales);
			productsForAll.add(productForPurchase);
			productsForAll.add(productForPurchase2);

			productService.saveAllProduct(productsForAll);

			PersonTypes personTypes = new PersonTypes();
			personTypes.setDescription("Cliente");
			PersonTypes personTypes2 = new PersonTypes();
			personTypes2.setDescription("Fornecedor");
			PersonTypes personTypes3 = new PersonTypes();
			personTypes3.setDescription("Vendedor");

			List<PersonTypes> pTypesListAll = new ArrayList<>();
			pTypesListAll.add(personTypes);
			pTypesListAll.add(personTypes2);
			pTypesListAll.add(personTypes3);

			personTypesService.saveAllTypes(pTypesListAll);

			Person client = new Person();
			client.setType(personTypes);
			client.setName("José Antonio Silva");
			client.setNumDoc("12345 SSP-DF");
			client.setEmail("jose.silva@gmail.com");
			client.setPhone("+5561991123876");
			client.setAddress("Rua das bananeiras sem número na baixa d'égua");

			Person seller = new Person();
			seller.setType(personTypes3);
			seller.setName("Luzinete Ferreira");
			seller.setNumDoc("54321 SSP-FZ");
			seller.setEmail("Luzinete.Ferreira@gmail.com");
			seller.setPhone("+5561991123444");
			seller.setAddress("Rua qualquer das coisas");

			Person store = new Person();
			store.setType(personTypes2);
			store.setName("Lana Rosa");
			store.setNumDoc("00.123.456-78");
			store.setEmail("lana.rosa@gmail.com");
			store.setPhone("+556233523456");
			store.setAddress("Bernardo Sayão, Fama");

			List<Person> people = new ArrayList<>();
			people.add(client);
			people.add(seller);
			people.add(store);

			personService.saveAllPeople(people);

			PaymentTypes payTypes = new PaymentTypes();
			payTypes.setDescription("À vista");
			PaymentTypes payTypes2 = new PaymentTypes();
			payTypes2.setDescription("Cartão");
			PaymentTypes payTypes3 = new PaymentTypes();
			payTypes3.setDescription("Cheque");
			PaymentTypes payTypes4 = new PaymentTypes();
			payTypes4.setDescription("Boleto");

			List<PaymentTypes> payTypesListAll = new ArrayList<>();
			payTypesListAll.add(payTypes);
			payTypesListAll.add(payTypes2);
			payTypesListAll.add(payTypes3);
			payTypesListAll.add(payTypes4);

			paymentTypesService.saveAllPaymentTypes(payTypesListAll);
			
			Purchases purchases = new Purchases();
			purchases.setStore(store);
			purchases.setTotalPrice(1000.10);
			purchases.setOccurenceDate(LocalDate.of(2022,02,20));
			purchases.setNotes("Teste de entrada inicial de dados no bd");

			List<Purchases> purchasesList = new ArrayList<>();
			purchasesList.add(purchases);

			purchasesService.saveAllPurchases(purchasesList);		
			
			PurchaseProduct purchaseProduct = new PurchaseProduct(purchases, productForPurchase, 21);
			PurchaseProduct purchaseProduct2 = new PurchaseProduct(purchases, productForPurchase2, 13);

			List<PurchaseProduct> purchaseProducts = new ArrayList<>();
			purchaseProducts.add(purchaseProduct);
			purchaseProducts.add(purchaseProduct2);

			purchaseProductService.saveAllPurchaseProduct(purchaseProducts);

			PurchasePayment purchasePayment = new PurchasePayment(purchases, payTypes, 1, 500.10);
			PurchasePayment purchasePayment2 = new PurchasePayment(purchases, payTypes2, 5, 500.00);

			List<PurchasePayment> purchasePayments = new ArrayList<>();
			purchasePayments.add(purchasePayment);
			purchasePayments.add(purchasePayment2);

			purchasePaymentService.saveAllPurchasePayment(purchasePayments);

			Sales sales = new Sales();
			sales.setProductSale(product2);
			sales.setClient(client);
			sales.setQuantity(2);
			sales.setTotalPrice(2000.20);
			sales.setSaleDate(LocalDate.of(2022,03,28));
			sales.setNotes("teste de venda");

			List<Sales> salesList = new ArrayList<>();
			salesList.add(sales);

			salesService.saveAllPurchases(salesList);

			List<PurchaseProduct> pp = purchaseProductService.getAllPurchaseProduct();

			// pp.forEach(p -> {
			// 	Product prod = productService.findProductById(p.getPk().getProduct().getId());
			// });
			PurchaseProduct fetchpp = new PurchaseProduct();
			List<PurchaseProduct> fetchpps = new ArrayList<>();

			for (PurchaseProduct p : pp) {
				System.out.println("pk product id: " + p.getPk().getProduct().getId());
				Product prod = productService.findProductById(p.getPk().getProduct().getId());
				Purchases purch = purchasesService.findPurchasesById(p.getPk().getPurchase().getId());
				p.getPk().setProduct(prod);
				p.getPk().setPurchase(purch);
				fetchpps.add(p);
				System.out.println(p.getProduct().getId());
			}
			System.out.println(fetchpps);


			for (int i = 0; i < pp.size(); i++) {

				// Product prod = productService.findProductById(pp.get(i).getPk().getProduct().getId());
				// Purchases purch = purchasesService.findPurchasesById(pp.get(i).getPk().getPurchase().getId());

				// pp.get(i).getPk().setProduct(product);
				// pp.get(i).getPk().setPurchase(purch);
				
				// System.out.println("purchase/product qtd: " + pp.get(i).getQuantity());
				// // System.out.println("purchase product qtd: " + p.getProduct().getId());
				// System.out.println("product id: " + pp.get(i).getPk().getProduct().getId());
				// System.out.println("purchase id: " + pp.get(i).getPk().getPurchase().getId());
				// // System.out.println("product id: " + p.getPk().getProduct().getDescription());
				// System.out.println("purchase product qtd: " + prod.getDescription());
				// // System.out.println("purchase product qtd: " + p.getProduct().getDescription());
			}
		};
	}
}
