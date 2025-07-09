package com.wasay_a.progresstracker;

import com.wasay_a.progresstracker.controller.MenuController;
import com.wasay_a.progresstracker.dao.BookDaoImpl;
import com.wasay_a.progresstracker.dao.UserDaoImpl;
import com.wasay_a.progresstracker.view.Menu;

public class Main {
    public static void main(String[] args) {
        MenuController controller = new MenuController(new UserDaoImpl(), new BookDaoImpl(), new Menu());
        controller.start();
    }
}
