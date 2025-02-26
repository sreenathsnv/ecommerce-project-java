package com.trivium.ecomTerminal.repos;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.stream.Collectors;

import com.trivium.ecomTerminal.models.Order;
import com.trivium.ecomTerminal.models.User;

public class OrderRepo {

	private static final String FILE_PATH = "src/com/trivium/ecomTerminal/data/orders.txt";
	private static final String FILE_PATH_2 = "src/com/trivium/ecomTerminal/data/orders_cancelled.txt";
	private ArrayList<Order> orders = new ArrayList<Order>();
	private ArrayList<Order> cancelledOrders =  new ArrayList<Order>();

	public static OrderRepo getInstance() {

		return new OrderRepo();
	}

	
		
	public OrderRepo() {
		super();
		loadCancelledOrders();
		loadOrders();
	}



	public boolean placeOrder(Order order) {

		boolean isOrder = orders.add(order);
		saveOrders();
		return isOrder;
	}

	public ArrayList<Order> getAllUserOrders(User user) {

		return orders.stream().filter(order -> order.getUser().getUsername().equalsIgnoreCase(user.getUsername()))
				.collect(Collectors.toCollection(ArrayList::new));

	}

	public ArrayList<Order> getAllUserCancelledOrders(User user) {

		return cancelledOrders.stream()
				.filter(order -> order.getUser().getUsername().equalsIgnoreCase(user.getUsername()))
				.collect(Collectors.toCollection(ArrayList::new));

	}

	public boolean cancelOrder(User user, int id) {

		Order order = getOrderById(id);

		if (order == null) {

			return false;
		} else {

			if (order.getUser().getId() == user.getId()) {
				orders.remove(order);
				saveOrders();
				cancelledOrders.add(order);
				saveCancelledOrders();
				return true;
			} else {

				return false;
			}

		}

	}

	public Order getOrderById(int id) {

		return orders.stream().filter(ord -> ord.getId() == id).findFirst().orElse(null);
	}
	
	@SuppressWarnings("unchecked")
	public void loadOrders() {
		
		File file = new File(FILE_PATH);
		if(!file.exists()) {
			return;
		}
		
		try(ObjectInputStream ois = new ObjectInputStream(new FileInputStream(FILE_PATH))){
			orders = (ArrayList<Order>) ois.readObject();
		}catch(IOException | ClassNotFoundException e) {
			e.printStackTrace();
		}
	}
	
	public void updateList() {
		saveOrders();
	}
	
	public void saveOrders() {
		try(ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(FILE_PATH))){
			oos.writeObject(orders);
		}catch(IOException e) {
			e.printStackTrace();
		}
	}

	@SuppressWarnings("unchecked")
	public void loadCancelledOrders() {
		
		File file = new File(FILE_PATH_2);
		if(!file.exists()) {
			return;
		}
		
		try(ObjectInputStream ois = new ObjectInputStream(new FileInputStream(FILE_PATH_2))){
			cancelledOrders = (ArrayList<Order>) ois.readObject();
		}catch(IOException | ClassNotFoundException e) {
			e.printStackTrace();
		}
	}
	
	public void saveCancelledOrders() {
		try(ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(FILE_PATH_2))){
			oos.writeObject(cancelledOrders);
		}catch(IOException e) {
			e.printStackTrace();
		}
	}


}
