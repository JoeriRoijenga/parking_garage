import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class Main {

	
	public Main() {
		setupGUI();
	}
	
	public static void main(String[] args) {
		Main main = new Main();

	}
	
	private void setupGUI() {
		JFrame mainFrame = new JFrame("Parkeersimulator");
		mainFrame.setSize(1200,800);
		mainFrame.setLayout(new GridLayout(1,1));
		
		mainFrame.addWindowListener(new WindowAdapter() {
	         public void windowClosing(WindowEvent windowEvent){
	            System.exit(0);
	         }
		});
		// create the menu
		JMenuBar menubar = new JMenuBar();
		mainFrame.setJMenuBar(menubar);
		JMenu settingsMenu = new JMenu("Settings");
		menubar.add(settingsMenu);
		JMenuItem settingsItem = new JMenuItem("Simulator settings");
		settingsItem.addActionListener(
				(ActionEvent  -> { showSettings(); })
				);
		settingsMenu.add(settingsItem);
		JMenuItem aboutItem = new JMenuItem("About");
		aboutItem.addActionListener(
				(ActionEvent  -> { showAbout(); })
				);
		settingsMenu.add(aboutItem);
		

		
		JTabbedPane viewPane = new JTabbedPane();		
		JComponent panel1 = makeTextPanel("Panel #1");
        viewPane.addTab("Tab 1", null, panel1,
                "Does nothing");        
        JComponent panel2 = makeTextPanel("Panel #2");
        viewPane.addTab("Tab 2", null, panel2,
                "Does twice as much nothing");      
        mainFrame.add(viewPane);       
        viewPane.setTabLayoutPolicy(JTabbedPane.SCROLL_TAB_LAYOUT);
		
		mainFrame.setVisible(true);
	}
	
	protected JComponent makeTextPanel(String text) {
        JPanel panel = new JPanel(false);
        JLabel filler = new JLabel(text);
        filler.setHorizontalAlignment(JLabel.CENTER);
        panel.setLayout(new GridLayout(1, 1));
        panel.add(filler);
        return panel;
    }
	private void showSettings() {
		JFrame settingsFrame = new JFrame("Setting");
		
		settingsFrame.setSize(500,500);
		settingsFrame.setLayout(new GridLayout(1,1));
		
		settingsFrame.addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent windowsEvent) {
				settingsFrame.setVisible(false);;
			}
		});
		
		settingsFrame.setVisible(true);
	}
	
	private void showAbout() {
		JFrame aboutFrame = new JFrame("About simulator");
		aboutFrame.setSize(500,200);
		aboutFrame.setLayout(new GridLayout(3,3));
		
		JLabel textLabel = new JLabel("Groningen CityPark simulator",JLabel.CENTER);
		textLabel.setFont(new Font("Sans", Font.PLAIN, 20));
		JLabel versionLabel = new JLabel("Version MVC-0.1.0",JLabel.CENTER);
		JLabel authorLabel = new JLabel("©2018, Joeri Roijenga, Niels de Vries, Tim Perdok en Timo de Jong");
		aboutFrame.addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent windowsEvent) {
				aboutFrame.setVisible(false);;
			}
		});
		
		aboutFrame.add(textLabel);
		aboutFrame.add(versionLabel);
		aboutFrame.add(authorLabel);
		aboutFrame.setVisible(true);
	}
	
}