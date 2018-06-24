package main.ui;

import main.config.SiegeConfigBroker;

import javax.swing.*;
import java.awt.event.*;

import static main.config.SiegeConfigBroker.*;

public class SiegeDataCenterSwitcher extends JDialog {
  private JPanel contentPane;
  private JButton buttonOK;
  private JButton buttonCancel;
  private JComboBox<String> dataCenterComboBox;
  private JButton buttonChooseConfigFolder;

  public SiegeDataCenterSwitcher() {
    setContentPane(contentPane);
    setModal(true);
    getRootPane().setDefaultButton(buttonOK);

    buttonOK.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent e) {
        onOK();
      }
    });

    buttonCancel.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent e) {
        onCancel();
      }
    });

    buttonChooseConfigFolder.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent e) {
        onChooseConfigFolder();
      }
    });


    // call onCancel() when cross is clicked
    setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
    addWindowListener(new WindowAdapter() {
      public void windowClosing(WindowEvent e) {
        onCancel();
      }
    });

    // call onCancel() on ESCAPE
    contentPane.registerKeyboardAction(new ActionListener() {
      public void actionPerformed(ActionEvent e) {
        onCancel();
      }
    }, KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE, 0), JComponent.WHEN_ANCESTOR_OF_FOCUSED_COMPONENT);
    populateDataCenterComboBox();
  }

  private void onOK() {
    // add your code here
    changeDataCenter(
        getValidDataCenters()[dataCenterComboBox.getSelectedIndex()]);
    dispose();
  }

  private void onCancel() {
    // add your code here if necessary
    dispose();
  }

  private void populateDataCenterComboBox() {
    String[] dataCenters = getValidDataCenters();
    for (String dataCenter : dataCenters) {
      dataCenterComboBox.addItem(dataCenter);
    }
  }

  private void onChooseConfigFolder() {
    JFileChooser chooser = new JFileChooser();
    chooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
    chooser.setCurrentDirectory(getConfigDirectory());
    int returnVal = chooser.showOpenDialog(buttonChooseConfigFolder);
    if(returnVal == JFileChooser.APPROVE_OPTION) {
      SiegeConfigBroker.setConfigDirectory(chooser.getSelectedFile().getPath());
    }
  }

  public static void main(String[] args) {
    SiegeDataCenterSwitcher dialog = new SiegeDataCenterSwitcher();
    dialog.pack();
    dialog.setVisible(true);
    System.exit(0);
  }
}
