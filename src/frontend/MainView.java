/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package frontend;

import common.User;
import input.PointerListener;
import javax.swing.Action;
import javax.swing.TransferHandler;
import jwinpointer.JWinPointerReader;

/**
 *
 * @author nickz
 */
public class MainView extends javax.swing.JFrame {
    
    private final MainViewController mvc;
    private final MenuController mc;
    private final AllSessionsModel model;
    private JWinPointerReader pointerReader;
    PointerListener pointerListener;
    
    public MainView() {
        this.mvc = new MainViewController();
        this.mc = new MenuController();
        this.model = new AllSessionsModel();
        this.pointerListener = new PointerListener(User.getLocalUser().pointerInfo());
        this.mvc.setModel(model);
        this.mc.setModel(model);
        initComponents();
    }
    
    public final void finishInit() {
        this.mvc.setMainView(this);
        this.mc.setMainView(this);
        this.model.setMainViewController(mvc);
        this.pointerReader = new JWinPointerReader(this);
        this.pointerReader.addPointerEventListener(pointerListener);
        initHandlers();
        mvc.menuUpdate();
    }
    
    private void initHandlers() {
        this.documentTabbedPane.finishInit();
        this.newMenuItem.addActionListener(this.mc.new NewFileAction());
        this.quitMenuItem.addActionListener(this.mc.new QuitAction());
        this.openMenuItem.addActionListener(this.mc.new OpenFileAction());
        this.saveMenuItem.addActionListener(this.mc.new SaveFileAction());
        this.saveAsMenuItem.addActionListener(this.mc.new SaveAsFileAction());
        this.exportMenuItem.addActionListener(this.mc.new ExportFileAction());
        
        this.undoMenuItem.addActionListener(this.mc.new UndoAction());
        this.redoMenuItem.addActionListener(this.mc.new RedoAction());
        
        this.cutMenuItem.setActionCommand((String) TransferHandler.getCutAction().getValue(Action.NAME));
        this.cutMenuItem.setAction(TransferHandler.getCutAction());
        
        this.newRasterLayerMenuItem.addActionListener(this.mc.new NewRasterLayerAction());
        
        this.connectMenuItem.addActionListener(this.mc.new ConnectAction());
        this.disconnectMenuItem.addActionListener(mc.new DisconnectAction());
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        toolbar = new frontend.tools.Toolbar();
        subToolbarSplitPane = new javax.swing.JSplitPane();
        jPanel1 = new javax.swing.JPanel();
        colorPalette2 = new frontend.tools.ColorToolbar();
        layerList = new frontend.layerlist.LayerList();
        documentTabbedPane = new SubstratePane(this.mvc);
        mainMenuBar = new javax.swing.JMenuBar();
        fileMenu = new javax.swing.JMenu();
        newMenuItem = new javax.swing.JMenuItem();
        openMenuItem = new javax.swing.JMenuItem();
        recentMenu = new javax.swing.JMenu();
        jSeparator11 = new javax.swing.JPopupMenu.Separator();
        recentClearMenuItem = new javax.swing.JMenuItem();
        jSeparator1 = new javax.swing.JPopupMenu.Separator();
        closeMenuItem = new javax.swing.JMenuItem();
        saveMenuItem = new javax.swing.JMenuItem();
        saveAsMenuItem = new javax.swing.JMenuItem();
        jSeparator2 = new javax.swing.JPopupMenu.Separator();
        importMenuItem = new javax.swing.JMenuItem();
        exportMenuItem = new javax.swing.JMenuItem();
        jSeparator3 = new javax.swing.JPopupMenu.Separator();
        preferencesMenuItem = new javax.swing.JMenuItem();
        shortcutSettingsMenuItem = new javax.swing.JMenuItem();
        tabletSettingsMenuItem = new javax.swing.JMenuItem();
        jSeparator4 = new javax.swing.JPopupMenu.Separator();
        quitMenuItem = new javax.swing.JMenuItem();
        editMenu = new javax.swing.JMenu();
        undoMenuItem = new javax.swing.JMenuItem();
        redoMenuItem = new javax.swing.JMenuItem();
        jSeparator5 = new javax.swing.JPopupMenu.Separator();
        cutMenuItem = new javax.swing.JMenuItem();
        copyMenuItem = new javax.swing.JMenuItem();
        pasteMenuItem = new javax.swing.JMenuItem();
        jSeparator6 = new javax.swing.JPopupMenu.Separator();
        clearMenuItem = new javax.swing.JMenuItem();
        fillMenuItem = new javax.swing.JMenuItem();
        advancedFillMenuItem = new javax.swing.JMenuItem();
        jSeparator7 = new javax.swing.JPopupMenu.Separator();
        tonalCorrectionMenu = new javax.swing.JMenu();
        transformMenu = new javax.swing.JMenu();
        jSeparator8 = new javax.swing.JPopupMenu.Separator();
        changeResolutionMenuItem = new javax.swing.JMenuItem();
        changeCanvasSizeMenuItem = new javax.swing.JMenuItem();
        cropMenuItem = new javax.swing.JMenuItem();
        layerPropertiesMenuItem = new javax.swing.JMenuItem();
        jSeparator9 = new javax.swing.JPopupMenu.Separator();
        bufferMenuItem = new javax.swing.JMenuItem();
        imageMenu = new javax.swing.JMenu();
        layerMenu = new javax.swing.JMenu();
        newLayerMenu = new javax.swing.JMenu();
        newRasterLayerMenuItem = new javax.swing.JMenuItem();
        newLayerFolderMenuItem = new javax.swing.JMenuItem();
        duplicateLayerMenuItem = new javax.swing.JMenuItem();
        deleteLayerMenuItem = new javax.swing.JMenuItem();
        selectionMenu = new javax.swing.JMenu();
        selectAllMenuItem = new javax.swing.JMenuItem();
        deselectMenuItem = new javax.swing.JMenuItem();
        invertSelectionMenuItem = new javax.swing.JMenuItem();
        viewMenu = new javax.swing.JMenu();
        collaborateMenu = new javax.swing.JMenu();
        connectMenuItem = new javax.swing.JMenuItem();
        disconnectMenuItem = new javax.swing.JMenuItem();
        jSeparator10 = new javax.swing.JPopupMenu.Separator();
        connectionSettingsMenuItem = new javax.swing.JMenuItem();
        animationMenu = new javax.swing.JMenu();
        jMenuItem1 = new javax.swing.JMenuItem();
        gfxMenu = new javax.swing.JMenu();
        windowMenu = new javax.swing.JMenu();
        jMenuItem3 = new javax.swing.JMenuItem();
        helpMenu = new javax.swing.JMenu();
        aboutMenuItem = new javax.swing.JMenuItem();
        versionMenuItem = new javax.swing.JMenuItem();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Jraw");
        setFocusable(false);
        setMinimumSize(new java.awt.Dimension(800, 600));
        setName("mainframe"); // NOI18N

        toolbar.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        toolbar.setFloatable(false);
        toolbar.setOrientation(javax.swing.SwingConstants.VERTICAL);
        toolbar.setRollover(true);
        toolbar.setToolTipText("");
        toolbar.setFocusable(false);
        toolbar.setMinimumSize(new java.awt.Dimension(40, 40));
        toolbar.setPreferredSize(new java.awt.Dimension(40, 10));

        subToolbarSplitPane.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        subToolbarSplitPane.setDividerLocation(-1);
        subToolbarSplitPane.setDividerSize(8);
        subToolbarSplitPane.setOrientation(javax.swing.JSplitPane.VERTICAL_SPLIT);
        subToolbarSplitPane.setFocusable(false);

        jPanel1.setFocusable(false);
        jPanel1.setPreferredSize(new java.awt.Dimension(169, 500));

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 178, Short.MAX_VALUE)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 500, Short.MAX_VALUE)
        );

        subToolbarSplitPane.setTopComponent(jPanel1);
        subToolbarSplitPane.setRightComponent(colorPalette2);

        layerList.setMinimumSize(new java.awt.Dimension(216, 300));

        documentTabbedPane.setBorder(javax.swing.BorderFactory.createCompoundBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.LOWERED), javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED)));

        mainMenuBar.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        mainMenuBar.setFocusable(false);
        mainMenuBar.setMaximumSize(new java.awt.Dimension(0, 40));
        mainMenuBar.setPreferredSize(new java.awt.Dimension(109, 30));

        fileMenu.setText("File");
        fileMenu.setFocusable(false);

        newMenuItem.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_N, java.awt.event.InputEvent.CTRL_MASK));
        newMenuItem.setText("New...");
        fileMenu.add(newMenuItem);

        openMenuItem.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_O, java.awt.event.InputEvent.CTRL_MASK));
        openMenuItem.setText("Open...");
        fileMenu.add(openMenuItem);

        recentMenu.setText("Recent Files");
        recentMenu.add(jSeparator11);

        recentClearMenuItem.setText("Clear Recent Files");
        recentMenu.add(recentClearMenuItem);

        fileMenu.add(recentMenu);
        fileMenu.add(jSeparator1);

        closeMenuItem.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_W, java.awt.event.InputEvent.CTRL_MASK));
        closeMenuItem.setText("Close File");
        closeMenuItem.setEnabled(false);
        fileMenu.add(closeMenuItem);

        saveMenuItem.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_S, java.awt.event.InputEvent.CTRL_MASK));
        saveMenuItem.setText("Save");
        saveMenuItem.setEnabled(false);
        fileMenu.add(saveMenuItem);

        saveAsMenuItem.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_S, java.awt.event.InputEvent.ALT_MASK | java.awt.event.InputEvent.SHIFT_MASK));
        saveAsMenuItem.setText("Save As...");
        saveAsMenuItem.setEnabled(false);
        fileMenu.add(saveAsMenuItem);
        fileMenu.add(jSeparator2);

        importMenuItem.setText("Import...");
        fileMenu.add(importMenuItem);

        exportMenuItem.setText("Export Visible...");
        fileMenu.add(exportMenuItem);
        fileMenu.add(jSeparator3);

        preferencesMenuItem.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_K, java.awt.event.InputEvent.CTRL_MASK));
        preferencesMenuItem.setText("Preferences...");
        fileMenu.add(preferencesMenuItem);

        shortcutSettingsMenuItem.setText("Shortcut Settings...");
        fileMenu.add(shortcutSettingsMenuItem);

        tabletSettingsMenuItem.setText("Tablet Settings...");
        fileMenu.add(tabletSettingsMenuItem);
        fileMenu.add(jSeparator4);

        quitMenuItem.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_Q, java.awt.event.InputEvent.CTRL_MASK));
        quitMenuItem.setText("Quit");
        fileMenu.add(quitMenuItem);

        mainMenuBar.add(fileMenu);

        editMenu.setText("Edit");
        editMenu.setFocusable(false);

        undoMenuItem.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_Z, java.awt.event.InputEvent.CTRL_MASK));
        undoMenuItem.setText("Undo");
        editMenu.add(undoMenuItem);

        redoMenuItem.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_Y, java.awt.event.InputEvent.CTRL_MASK));
        redoMenuItem.setText("Redo");
        editMenu.add(redoMenuItem);
        editMenu.add(jSeparator5);

        cutMenuItem.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_X, java.awt.event.InputEvent.CTRL_MASK));
        cutMenuItem.setText("Cut");
        editMenu.add(cutMenuItem);

        copyMenuItem.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_C, java.awt.event.InputEvent.CTRL_MASK));
        copyMenuItem.setText("Copy");
        editMenu.add(copyMenuItem);

        pasteMenuItem.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_V, java.awt.event.InputEvent.CTRL_MASK));
        pasteMenuItem.setText("Paste");
        editMenu.add(pasteMenuItem);
        editMenu.add(jSeparator6);

        clearMenuItem.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_DELETE, 0));
        clearMenuItem.setText("Clear");
        editMenu.add(clearMenuItem);

        fillMenuItem.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_DELETE, java.awt.event.InputEvent.ALT_MASK));
        fillMenuItem.setText("Fill");
        editMenu.add(fillMenuItem);

        advancedFillMenuItem.setText("Advanced Fill...");
        editMenu.add(advancedFillMenuItem);
        editMenu.add(jSeparator7);

        tonalCorrectionMenu.setText("Tonal Correction");
        editMenu.add(tonalCorrectionMenu);

        transformMenu.setText("Transform");
        editMenu.add(transformMenu);
        editMenu.add(jSeparator8);

        changeResolutionMenuItem.setText("Change Resolution...");
        editMenu.add(changeResolutionMenuItem);

        changeCanvasSizeMenuItem.setText("Change Canvas Size...");
        editMenu.add(changeCanvasSizeMenuItem);

        cropMenuItem.setText("Crop");
        editMenu.add(cropMenuItem);

        layerPropertiesMenuItem.setText("Layer Properties...");
        editMenu.add(layerPropertiesMenuItem);
        editMenu.add(jSeparator9);

        bufferMenuItem.setText("Buffer...");
        editMenu.add(bufferMenuItem);

        mainMenuBar.add(editMenu);

        imageMenu.setText("Image");
        mainMenuBar.add(imageMenu);

        layerMenu.setText("Layer");
        layerMenu.setFocusable(false);

        newLayerMenu.setText("New Layer");

        newRasterLayerMenuItem.setText("New Raster Layer");
        newLayerMenu.add(newRasterLayerMenuItem);

        layerMenu.add(newLayerMenu);

        newLayerFolderMenuItem.setText("New Layer Folder");
        layerMenu.add(newLayerFolderMenuItem);

        duplicateLayerMenuItem.setText("Duplicate Layer");
        layerMenu.add(duplicateLayerMenuItem);

        deleteLayerMenuItem.setText("Delete Layer");
        layerMenu.add(deleteLayerMenuItem);

        mainMenuBar.add(layerMenu);

        selectionMenu.setText("Selection");
        selectionMenu.setFocusable(false);

        selectAllMenuItem.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_A, java.awt.event.InputEvent.CTRL_MASK));
        selectAllMenuItem.setText("Select All");
        selectionMenu.add(selectAllMenuItem);

        deselectMenuItem.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_D, java.awt.event.InputEvent.CTRL_MASK));
        deselectMenuItem.setText("Deselect");
        selectionMenu.add(deselectMenuItem);

        invertSelectionMenuItem.setText("Invert Selection");
        selectionMenu.add(invertSelectionMenuItem);

        mainMenuBar.add(selectionMenu);

        viewMenu.setText("View");
        viewMenu.setFocusable(false);
        mainMenuBar.add(viewMenu);

        collaborateMenu.setText("Server");

        connectMenuItem.setText("Connect...");
        collaborateMenu.add(connectMenuItem);

        disconnectMenuItem.setText("Disconnect");
        collaborateMenu.add(disconnectMenuItem);
        collaborateMenu.add(jSeparator10);

        connectionSettingsMenuItem.setText("Connection Settings...");
        collaborateMenu.add(connectionSettingsMenuItem);

        mainMenuBar.add(collaborateMenu);

        animationMenu.setText("Animation");
        animationMenu.setFocusable(false);

        jMenuItem1.setText("jMenuItem1");
        animationMenu.add(jMenuItem1);

        mainMenuBar.add(animationMenu);

        gfxMenu.setText("GFX");
        gfxMenu.setFocusable(false);
        mainMenuBar.add(gfxMenu);

        windowMenu.setText("Window");

        jMenuItem3.setText("jMenuItem3");
        windowMenu.add(jMenuItem3);

        mainMenuBar.add(windowMenu);

        helpMenu.setText("Help");

        aboutMenuItem.setText("About...");
        helpMenu.add(aboutMenuItem);

        versionMenuItem.setText("Version Information...");
        helpMenu.add(versionMenuItem);

        mainMenuBar.add(helpMenu);

        setJMenuBar(mainMenuBar);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(toolbar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(subToolbarSplitPane, javax.swing.GroupLayout.PREFERRED_SIZE, 184, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(documentTabbedPane, javax.swing.GroupLayout.PREFERRED_SIZE, 930, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(layerList, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(toolbar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(subToolbarSplitPane)
            .addComponent(documentTabbedPane, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(layerList, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    // Variables declaration - do not modify//GEN-BEGIN:variables
    public javax.swing.JMenuItem aboutMenuItem;
    public javax.swing.JMenuItem advancedFillMenuItem;
    private javax.swing.JMenu animationMenu;
    public javax.swing.JMenuItem bufferMenuItem;
    public javax.swing.JMenuItem changeCanvasSizeMenuItem;
    public javax.swing.JMenuItem changeResolutionMenuItem;
    public javax.swing.JMenuItem clearMenuItem;
    public javax.swing.JMenuItem closeMenuItem;
    private javax.swing.JMenu collaborateMenu;
    private frontend.tools.ColorToolbar colorPalette2;
    public javax.swing.JMenuItem connectMenuItem;
    public javax.swing.JMenuItem connectionSettingsMenuItem;
    public javax.swing.JMenuItem copyMenuItem;
    public javax.swing.JMenuItem cropMenuItem;
    public javax.swing.JMenuItem cutMenuItem;
    public javax.swing.JMenuItem deleteLayerMenuItem;
    public javax.swing.JMenuItem deselectMenuItem;
    public javax.swing.JMenuItem disconnectMenuItem;
    public frontend.SubstratePane documentTabbedPane;
    public javax.swing.JMenuItem duplicateLayerMenuItem;
    public javax.swing.JMenu editMenu;
    public javax.swing.JMenuItem exportMenuItem;
    public javax.swing.JMenu fileMenu;
    public javax.swing.JMenuItem fillMenuItem;
    private javax.swing.JMenu gfxMenu;
    private javax.swing.JMenu helpMenu;
    private javax.swing.JMenu imageMenu;
    public javax.swing.JMenuItem importMenuItem;
    public javax.swing.JMenuItem invertSelectionMenuItem;
    private javax.swing.JMenuItem jMenuItem1;
    private javax.swing.JMenuItem jMenuItem3;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPopupMenu.Separator jSeparator1;
    private javax.swing.JPopupMenu.Separator jSeparator10;
    private javax.swing.JPopupMenu.Separator jSeparator11;
    private javax.swing.JPopupMenu.Separator jSeparator2;
    private javax.swing.JPopupMenu.Separator jSeparator3;
    private javax.swing.JPopupMenu.Separator jSeparator4;
    private javax.swing.JPopupMenu.Separator jSeparator5;
    private javax.swing.JPopupMenu.Separator jSeparator6;
    private javax.swing.JPopupMenu.Separator jSeparator7;
    private javax.swing.JPopupMenu.Separator jSeparator8;
    private javax.swing.JPopupMenu.Separator jSeparator9;
    public frontend.layerlist.LayerList layerList;
    public javax.swing.JMenu layerMenu;
    public javax.swing.JMenuItem layerPropertiesMenuItem;
    private javax.swing.JMenuBar mainMenuBar;
    public javax.swing.JMenuItem newLayerFolderMenuItem;
    public javax.swing.JMenu newLayerMenu;
    public javax.swing.JMenuItem newMenuItem;
    public javax.swing.JMenuItem newRasterLayerMenuItem;
    public javax.swing.JMenuItem openMenuItem;
    public javax.swing.JMenuItem pasteMenuItem;
    public javax.swing.JMenuItem preferencesMenuItem;
    public javax.swing.JMenuItem quitMenuItem;
    public javax.swing.JMenuItem recentClearMenuItem;
    public javax.swing.JMenu recentMenu;
    public javax.swing.JMenuItem redoMenuItem;
    public javax.swing.JMenuItem saveAsMenuItem;
    public javax.swing.JMenuItem saveMenuItem;
    public javax.swing.JMenuItem selectAllMenuItem;
    public javax.swing.JMenu selectionMenu;
    public javax.swing.JMenuItem shortcutSettingsMenuItem;
    private javax.swing.JSplitPane subToolbarSplitPane;
    public javax.swing.JMenuItem tabletSettingsMenuItem;
    public javax.swing.JMenu tonalCorrectionMenu;
    private frontend.tools.Toolbar toolbar;
    public javax.swing.JMenu transformMenu;
    public javax.swing.JMenuItem undoMenuItem;
    public javax.swing.JMenuItem versionMenuItem;
    private javax.swing.JMenu viewMenu;
    private javax.swing.JMenu windowMenu;
    // End of variables declaration//GEN-END:variables

}
