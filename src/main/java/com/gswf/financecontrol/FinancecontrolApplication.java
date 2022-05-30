package com.gswf.financecontrol;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import com.gswf.financecontrol.model.Business;
import com.gswf.financecontrol.model.BusinessPayment;
import com.gswf.financecontrol.model.BusinessProduct;
import com.gswf.financecontrol.model.BusinessTypes;
import com.gswf.financecontrol.model.PaymentTypes;
import com.gswf.financecontrol.model.Person;
import com.gswf.financecontrol.model.PersonTypes;
import com.gswf.financecontrol.model.Product;
import com.gswf.financecontrol.service.BusinessPaymentService;
import com.gswf.financecontrol.service.BusinessProductService;
import com.gswf.financecontrol.service.BusinessService;
import com.gswf.financecontrol.service.BusinessTypesService;
import com.gswf.financecontrol.service.PaymentTypesService;
import com.gswf.financecontrol.service.PersonService;
import com.gswf.financecontrol.service.PersonTypesService;
import com.gswf.financecontrol.service.ProductService;

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
									PersonService personService, ProductService productService, BusinessProductService businessProductService,
									BusinessService businessService, BusinessPaymentService businessPaymentService,
									BusinessTypesService businessTypesService) {
		return args -> {
			businessService.deleteAllBusiness();
			productService.deleteAllProducts();
			personService.deleteAllPeople();
			personTypesService.deleteAllPersonTypes();
			businessProductService.deleteAllBusinessProduct();
			businessPaymentService.deleteAllBusinessPayment();
			paymentTypesService.deleteAllPaymentTypes();
			businessTypesService.deleteAllBusinessTypes();

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
						
			List<Product> productsForAll2 = new ArrayList<>();
			productsForAll2.add(product2);
			productsForAll2.add(product5);

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

			Person client2 = new Person();
			client2.setType(personTypes);
			client2.setName("Francisco Roque");
			client2.setNumDoc("654321 SSP-MG");
			client2.setEmail("chico.roque@gmail.com");
			client2.setPhone("+556198543215");
			client2.setAddress("Av. Caribenha nº 1534, Res Altaneira, Apto 345, torre B");

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

			Person store2 = new Person();
			store2.setType(personTypes2);
			store2.setName("Unica");
			store2.setNumDoc("00.321.654-87");
			store2.setEmail("client@unica.com");
			store2.setPhone("+556232231234");
			store2.setAddress("Av. Goiás, Criméia");

			List<Person> people = new ArrayList<>();
			people.add(client);
			people.add(client2);
			people.add(seller);
			people.add(store);
			people.add(store2);

			personService.saveAllPeople(people);

			PaymentTypes payTypes = new PaymentTypes();
			payTypes.setDescription("Dinheiro");
			PaymentTypes payTypes2 = new PaymentTypes();
			payTypes2.setDescription("Cartão Crédito");
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
			
			List<PaymentTypes> payTypesList = new ArrayList<>();
			payTypesList.add(payTypes);
			payTypesList.add(payTypes2);
			
			List<PaymentTypes> payTypesList2 = new ArrayList<>();
			payTypesList2.add(payTypes);
			payTypesList2.add(payTypes2);

			BusinessTypes businessType = new BusinessTypes();
			businessType.setDescription("Compra");
			BusinessTypes businessType2 = new BusinessTypes();
			businessType2.setDescription("Venda");

			List<BusinessTypes> businessTypes = new ArrayList<>();
			businessTypes.add(businessType);
			businessTypes.add(businessType2);

			businessTypesService.saveAllTypes(businessTypes);

			Business purchases = new Business();
			purchases.setStore(store);
			purchases.setBusinessType(businessType);
			purchases.setBusinessDate(LocalDate.of(2022,02,20));
			purchases.setNotes("Teste de entrada inicial de dados no bd");
			
			Business purchases2 = new Business();
			purchases2.setStore(store2);
			purchases2.setBusinessType(businessType);
			purchases2.setBusinessDate(LocalDate.of(2022,04,11));
			purchases2.setNotes("Testa outra compra");

			List<Business> purchasesList = new ArrayList<>();
			purchasesList.add(purchases);
			purchasesList.add(purchases2);

			businessService.saveAllBusiness(purchasesList);		
			
			BusinessProduct purchaseProduct = new BusinessProduct();
			purchaseProduct.setQuantity(21);
			purchaseProduct.setBusiness(purchases);
			purchaseProduct.setProduct(productForPurchase);
			purchaseProduct.setPrice(235.00);

			BusinessProduct purchaseProduct2 = new BusinessProduct();
			purchaseProduct2.setQuantity(12);
			purchaseProduct2.setBusiness(purchases);
			purchaseProduct2.setProduct(productForPurchase2);
			purchaseProduct2.setPrice(18.28);

			purchases.getBusinessProducts().add(purchaseProduct);
			purchases.getBusinessProducts().add(purchaseProduct2);

			BusinessProduct purchaseProduct3 = new BusinessProduct();
			purchaseProduct3.setQuantity(45);
			purchaseProduct3.setBusiness(purchases2);
			purchaseProduct3.setProduct(product3);
			purchaseProduct3.setPrice(18.29);

			BusinessProduct purchaseProduct4 = new BusinessProduct();
			purchaseProduct4.setQuantity(50);
			purchaseProduct4.setBusiness(purchases2);
			purchaseProduct4.setProduct(product5);
			purchaseProduct4.setPrice(21.33);

			purchases2.getBusinessProducts().add(purchaseProduct3);
			purchases2.getBusinessProducts().add(purchaseProduct4);

			List<BusinessProduct> purchaseProducts = new ArrayList<>();
			purchaseProducts.add(purchaseProduct);
			purchaseProducts.add(purchaseProduct2);
			purchaseProducts.add(purchaseProduct3);
			purchaseProducts.add(purchaseProduct4);

			businessProductService.saveAllBusinessProduct(purchaseProducts);

			BusinessPayment purchasePayment = new BusinessPayment();
			purchasePayment.setInstallment(1);
			purchasePayment.setAmount(500.10);
			purchasePayment.setPayDate(LocalDate.of(2022,02,20));
			purchasePayment.setPayed(true);
			purchasePayment.setBusiness(purchases);
			purchasePayment.setPayment(payTypes);

			BusinessPayment purchasePayment2 = new BusinessPayment();
			purchasePayment2.setInstallment(1);
			purchasePayment2.setAmount(930.85);
			purchasePayment2.setPayDate(LocalDate.of(2022,03,20));
			purchasePayment2.setPayed(false);
			purchasePayment2.setBusiness(purchases);
			purchasePayment2.setPayment(payTypes2);

			BusinessPayment purchasePayment22 = new BusinessPayment();
			purchasePayment22.setInstallment(2);
			purchasePayment22.setAmount(930.85);
			purchasePayment22.setPayDate(LocalDate.of(2022,04,20));
			purchasePayment22.setPayed(false);
			purchasePayment22.setBusiness(purchases);
			purchasePayment22.setPayment(payTypes2);

			BusinessPayment purchasePayment23 = new BusinessPayment();
			purchasePayment23.setInstallment(3);
			purchasePayment23.setAmount(930.85);
			purchasePayment23.setPayDate(LocalDate.of(2022,05,20));
			purchasePayment23.setPayed(false);
			purchasePayment23.setBusiness(purchases);
			purchasePayment23.setPayment(payTypes2);

			BusinessPayment purchasePayment24 = new BusinessPayment();
			purchasePayment24.setInstallment(4);
			purchasePayment24.setAmount(930.85);
			purchasePayment24.setPayDate(LocalDate.of(2022,06,20));
			purchasePayment24.setPayed(false);
			purchasePayment24.setBusiness(purchases);
			purchasePayment24.setPayment(payTypes2);

			BusinessPayment purchasePayment25 = new BusinessPayment();
			purchasePayment25.setInstallment(5);
			purchasePayment25.setAmount(930.85);
			purchasePayment25.setPayDate(LocalDate.of(2022,07,20));
			purchasePayment25.setPayed(false);
			purchasePayment25.setBusiness(purchases);
			purchasePayment25.setPayment(payTypes2);

			purchases.getBusinessPayments().add(purchasePayment);
			purchases.getBusinessPayments().add(purchasePayment2);
			purchases.getBusinessPayments().add(purchasePayment22);
			purchases.getBusinessPayments().add(purchasePayment23);
			purchases.getBusinessPayments().add(purchasePayment24);
			purchases.getBusinessPayments().add(purchasePayment25);

			BusinessPayment purchasePayment3 = new BusinessPayment();
			purchasePayment3.setInstallment(1);
			purchasePayment3.setAmount(314.92);
			purchasePayment3.setPayDate(LocalDate.of(2022,07,20));
			purchasePayment3.setPayed(false);
			purchasePayment3.setBusiness(purchases2);
			purchasePayment3.setPayment(payTypes3);

			BusinessPayment purchasePayment32 = new BusinessPayment();
			purchasePayment32.setInstallment(2);
			purchasePayment32.setAmount(314.92);
			purchasePayment32.setPayDate(LocalDate.of(2022,8,20));
			purchasePayment32.setPayed(false);
			purchasePayment32.setBusiness(purchases2);
			purchasePayment32.setPayment(payTypes3);

			BusinessPayment purchasePayment33 = new BusinessPayment();
			purchasePayment33.setInstallment(3);
			purchasePayment33.setAmount(314.92);
			purchasePayment33.setPayDate(LocalDate.of(2022,9,20));
			purchasePayment33.setPayed(false);
			purchasePayment33.setBusiness(purchases2);
			purchasePayment33.setPayment(payTypes3);

			BusinessPayment purchasePayment34 = new BusinessPayment();
			purchasePayment34.setInstallment(4);
			purchasePayment34.setAmount(314.92);
			purchasePayment34.setPayDate(LocalDate.of(2022,10,20));
			purchasePayment34.setPayed(false);
			purchasePayment34.setBusiness(purchases2);
			purchasePayment34.setPayment(payTypes3);

			BusinessPayment purchasePayment35 = new BusinessPayment();
			purchasePayment35.setInstallment(5);
			purchasePayment35.setAmount(314.92);
			purchasePayment35.setPayDate(LocalDate.of(2022,11,20));
			purchasePayment35.setPayed(false);
			purchasePayment35.setBusiness(purchases2);
			purchasePayment35.setPayment(payTypes3);

			BusinessPayment purchasePayment36 = new BusinessPayment();
			purchasePayment36.setInstallment(6);
			purchasePayment36.setAmount(314.92);
			purchasePayment36.setPayDate(LocalDate.of(2022,11,20));
			purchasePayment36.setPayed(false);
			purchasePayment36.setBusiness(purchases2);
			purchasePayment36.setPayment(payTypes3);

			purchases2.getBusinessPayments().add(purchasePayment3);
			purchases2.getBusinessPayments().add(purchasePayment32);
			purchases2.getBusinessPayments().add(purchasePayment33);
			purchases2.getBusinessPayments().add(purchasePayment34);
			purchases2.getBusinessPayments().add(purchasePayment35);
			purchases2.getBusinessPayments().add(purchasePayment36);

			List<BusinessPayment> purchasePayments = new ArrayList<>();
			purchasePayments.add(purchasePayment);
			purchasePayments.add(purchasePayment2);
			purchasePayments.add(purchasePayment22);
			purchasePayments.add(purchasePayment23);
			purchasePayments.add(purchasePayment24);
			purchasePayments.add(purchasePayment25);
			purchasePayments.add(purchasePayment3);
			purchasePayments.add(purchasePayment32);
			purchasePayments.add(purchasePayment33);
			purchasePayments.add(purchasePayment34);
			purchasePayments.add(purchasePayment35);
			purchasePayments.add(purchasePayment36);

			businessPaymentService.saveAllBusinessPayment(purchasePayments);
			
			Business sale = new Business();
			sale.setStore(client);
			sale.setBusinessType(businessType2);
			sale.setBusinessDate(LocalDate.of(2022,05,07));
			sale.setNotes("Testa venda");
			
			Business sale2 = new Business();
			sale2.setStore(client2);
			sale2.setBusinessType(businessType2);
			sale2.setBusinessDate(LocalDate.of(2022,05,14));
			sale2.setNotes("Testa outra venda");

			List<Business> salesList = new ArrayList<>();
			salesList.add(sale);
			salesList.add(sale2);

			businessService.saveAllBusiness(salesList);	
						
			BusinessProduct productsSale = new BusinessProduct();
			productsSale.setQuantity(5);
			productsSale.setBusiness(sale);
			productsSale.setProduct(product3);
			productsSale.setPrice(95.00);
						
			BusinessProduct productsSale2 = new BusinessProduct();
			productsSale2.setQuantity(14);
			productsSale2.setBusiness(sale);
			productsSale2.setProduct(product5);
			productsSale2.setPrice(229.00);

			sale.getBusinessProducts().add(productsSale);
			sale.getBusinessProducts().add(productsSale2);
						
			BusinessProduct productsSale3 = new BusinessProduct();
			productsSale3.setQuantity(17);
			productsSale3.setBusiness(sale2);
			productsSale3.setProduct(product2);
			productsSale3.setPrice(198.00);
						
			BusinessProduct productsSale4 = new BusinessProduct();
			productsSale4.setQuantity(10);
			productsSale4.setBusiness(sale2);
			productsSale4.setProduct(product4);
			productsSale4.setPrice(330.00);

			sale2.getBusinessProducts().add(productsSale3);
			sale2.getBusinessProducts().add(productsSale4);

			List<BusinessProduct> saleProducts = new ArrayList<>();
			saleProducts.add(productsSale);
			saleProducts.add(productsSale2);
			saleProducts.add(productsSale3);
			saleProducts.add(productsSale4);

			businessProductService.saveAllBusinessProduct(saleProducts);

			BusinessPayment salesPayment = new BusinessPayment();
			salesPayment.setInstallment(1);
			salesPayment.setAmount(645.33);
			salesPayment.setPayDate(LocalDate.of(2022,05,07));
			salesPayment.setPayed(true);
			salesPayment.setBusiness(sale);
			salesPayment.setPayment(payTypes2);

			sale.getBusinessPayments().add(salesPayment);

			BusinessPayment salesPayment2 = new BusinessPayment();
			salesPayment2.setInstallment(1);
			salesPayment2.setAmount(246.91);
			salesPayment2.setPayDate(LocalDate.of(2022,06,14));
			salesPayment2.setPayed(true);
			salesPayment2.setBusiness(sale2);
			salesPayment2.setPayment(payTypes2);

			BusinessPayment salesPayment22 = new BusinessPayment();
			salesPayment22.setInstallment(2);
			salesPayment22.setAmount(246.91);
			salesPayment22.setPayDate(LocalDate.of(2022,07,14));
			salesPayment22.setPayed(false);
			salesPayment22.setBusiness(sale2);
			salesPayment22.setPayment(payTypes2);

			BusinessPayment salesPayment23 = new BusinessPayment();
			salesPayment23.setInstallment(3);
			salesPayment23.setAmount(246.91);
			salesPayment23.setPayDate(LocalDate.of(2022,8,14));
			salesPayment23.setPayed(false);
			salesPayment23.setBusiness(sale2);
			salesPayment23.setPayment(payTypes2);

			BusinessPayment salesPayment24 = new BusinessPayment();
			salesPayment24.setInstallment(4);
			salesPayment24.setAmount(246.91);
			salesPayment24.setPayDate(LocalDate.of(2022,9,14));
			salesPayment24.setPayed(false);
			salesPayment24.setBusiness(sale2);
			salesPayment24.setPayment(payTypes2);

			BusinessPayment salesPayment25 = new BusinessPayment();
			salesPayment25.setInstallment(5);
			salesPayment25.setAmount(246.91);
			salesPayment25.setPayDate(LocalDate.of(2022,10,14));
			salesPayment25.setPayed(false);
			salesPayment25.setBusiness(sale2);
			salesPayment25.setPayment(payTypes2);

			sale2.getBusinessPayments().add(salesPayment);
			sale2.getBusinessPayments().add(salesPayment2);
			sale2.getBusinessPayments().add(salesPayment22);
			sale2.getBusinessPayments().add(salesPayment23);
			sale2.getBusinessPayments().add(salesPayment24);
			sale2.getBusinessPayments().add(salesPayment25);

			List<BusinessPayment> salesPayments = new ArrayList<>();
			salesPayments.add(salesPayment);
			salesPayments.add(salesPayment2);
			salesPayments.add(salesPayment22);
			salesPayments.add(salesPayment23);
			salesPayments.add(salesPayment24);
			salesPayments.add(salesPayment25);

			businessPaymentService.saveAllBusinessPayment(salesPayments);

			// productForSales.getSaled().setId(sales.getId());

			// sales.getProducts().add(product);
			// sales.getProducts().add(product3);
			// sales.getProducts().add(product5);
			// sales.getProducts().add(product4);

		// 	List<PurchaseProduct> pp = purchaseProductService.getAllPurchaseProduct();

		// 	// pp.forEach(p -> {
		// 	// 	Product prod = productService.findProductById(p.getPk().getProduct().getId());
		// 	// });
		// 	PurchaseProduct fetchpp = new PurchaseProduct();
		// 	List<PurchaseProduct> fetchpps = new ArrayList<>();

		// 	for (PurchaseProduct p : pp) {
		// 		// System.out.println("pk product id: " + p.getPk().getProduct().getId());
		// 		// Product prod = productService.findProductById(p.getPk().getProduct().getId());
		// 		// Purchases purch = purchasesService.findPurchasesById(p.getPk().getPurchase().getId());
		// 		// p.getPk().setProduct(prod);
		// 		// p.getPk().setPurchase(purch);
		// 		// fetchpps.add(p);
		// 		// System.out.println(p.getProduct().getId());
		// 	}

		// 	fetchpps = purchaseProductService.getAllPurchaseByPurchaseId(2);
		// 	System.out.println(fetchpps);

		// 	List<Purchases> purch = purchasesService.getAllPurchases();

		// 	int count = 0;

		// 	for (Purchases p : purch) {
		// 		// List<PurchaseProduct> pps = purchaseProductService.fillFullPPFromList(p.getId().intValue());
				
		// 		// purch.get(count).setPurchaseProducts(pps);

		// 	}
		// 	// System.out.println(purch);

		};
	}
}
