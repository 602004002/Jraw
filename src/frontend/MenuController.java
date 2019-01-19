/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package frontend;

import common.Session;
import frontend.subforms.newfile.NewFileForm;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 *
 * @author nickz
 */
public class MenuController {

    private Session s;
    private final MainView mainview;

    public MenuController() {
        System.out.println("MenuController()");
        this.mainview = new MainView(this);
        this.mainview.setVisible(true);
        System.out.println("Main Window open");
    }

    class QuitAction implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            //check for save
            if (s == null || s.isSaved()) {
                System.exit(0);
            }
        }
    }

    class NewFileAction implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            Thread nf = new Thread(() -> {
                System.out.println("New file thread started");
                new NewFileForm(mainview.documentTabbedPane).setVisible(true);
            }, "New file thread");
            nf.start();
        }
    }

}
