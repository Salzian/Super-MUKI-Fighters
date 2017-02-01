
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JButton;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.SpringLayout;
import javax.swing.JCheckBox;
import javax.swing.JTabbedPane;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JSpinner;
import javax.swing.event.ChangeListener;

import javax.swing.event.ChangeEvent;
import java.awt.Color;
import javax.swing.UIManager;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;
import java.awt.Rectangle;
import java.awt.Cursor;

public class Configurator extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	
	public static void main(String[] args) {
		
		try {
			UIManager.setLookAndFeel("javax.swing.plaf.nimbus.NimbusLookAndFeel");
		} catch (Throwable e) {
			e.printStackTrace();
		}
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Configurator frame = new Configurator();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
		
	}
	
	static boolean fullscreen;
	static boolean opengl;
	static int width;
	static int height;
	
	public JCheckBox chckbxFullscreen = new JCheckBox("Fullscreen (auto-detect)");
	public JCheckBox chckbxOpenGL = new JCheckBox("OpenGL");
	public JLabel lblHeight = new JLabel("Height");
	public JLabel lblWidth = new JLabel("Width");
	public JPanel panel_1 = new JPanel();;
	public JButton btnSave = new JButton("Save");;
	public JButton btnSaveStart = new JButton("Save & Start");;
	public JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
	public final JSpinner spinnerHeight = new JSpinner();
	public final JSpinner spinnerWidth = new JSpinner();
	public final JLabel lblPx = new JLabel("px");
	public final JLabel lblPx2 = new JLabel("px");;

	/**
	 * Create the frame.
	 * @throws InterruptedException 
	 */
	
	public Configurator() throws InterruptedException {
		setMaximizedBounds(new Rectangle(0, 0, 0, 0));
		
		try {
			loadSettings();
		}
		catch (IOException e) {
			System.out.println("File Error. Trying to create new file...");
			try {
				Settings.CreateFile();
			}
			catch (IOException f) {
				System.out.println("File cannot be created. Check write access permissions! Loading game with default settings...");
			}
			try {
				loadSettings();
			} catch(IOException g) {
				System.out.println("New file cannot be read. Check read access permissions! Loading game with default settings...");
			}
			
		}
		
		chckbxFullscreen.setSelected(fullscreen);
		lblWidth.setEnabled(!fullscreen);
		lblHeight.setEnabled(!fullscreen);
		spinnerWidth.setEnabled(false);
		spinnerWidth.setCursor(Cursor.getPredefinedCursor(Cursor.TEXT_CURSOR));
		spinnerWidth.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent arg0) {
				width = (int) spinnerWidth.getValue();
			}
		});
		spinnerWidth.setValue(width);
		spinnerHeight.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent arg0) {
				height = (int) spinnerHeight.getValue();
			}
		});
		spinnerHeight.setEnabled(false);
		spinnerHeight.setCursor(Cursor.getPredefinedCursor(Cursor.TEXT_CURSOR));
		spinnerHeight.setValue(height);
		chckbxOpenGL.setSelected(opengl);

		setBackground(Color.DARK_GRAY);
		setTitle("Super MUKI Fighters c");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 335, 300);
		contentPane = new JPanel();
		contentPane.setBackground(Color.DARK_GRAY);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		
		tabbedPane.setForeground(Color.GRAY);
		tabbedPane.setBackground(Color.GRAY);
		
		panel_1.setBackground(Color.DARK_GRAY);
		tabbedPane.addTab("Graphics", null, panel_1, null);
		SpringLayout sl_panel_1 = new SpringLayout();
		sl_panel_1.putConstraint(SpringLayout.WEST, lblPx2, 6, SpringLayout.EAST, spinnerHeight);
		sl_panel_1.putConstraint(SpringLayout.SOUTH, lblPx2, 0, SpringLayout.SOUTH, lblHeight);
		sl_panel_1.putConstraint(SpringLayout.WEST, lblPx, 6, SpringLayout.EAST, spinnerWidth);
		sl_panel_1.putConstraint(SpringLayout.SOUTH, lblPx, 0, SpringLayout.SOUTH, lblWidth);
		sl_panel_1.putConstraint(SpringLayout.NORTH, spinnerWidth, 6, SpringLayout.SOUTH, chckbxFullscreen);
		sl_panel_1.putConstraint(SpringLayout.NORTH, lblHeight, 18, SpringLayout.SOUTH, lblWidth);
		sl_panel_1.putConstraint(SpringLayout.NORTH, lblWidth, 6, SpringLayout.NORTH, spinnerWidth);
		sl_panel_1.putConstraint(SpringLayout.EAST, lblWidth, 0, SpringLayout.EAST, lblHeight);
		sl_panel_1.putConstraint(SpringLayout.EAST, spinnerWidth, 0, SpringLayout.EAST, spinnerHeight);
		sl_panel_1.putConstraint(SpringLayout.WEST, spinnerHeight, 6, SpringLayout.EAST, lblHeight);
		sl_panel_1.putConstraint(SpringLayout.EAST, spinnerHeight, -173, SpringLayout.EAST, panel_1);
		sl_panel_1.putConstraint(SpringLayout.WEST, spinnerWidth, 0, SpringLayout.WEST, spinnerHeight);
		sl_panel_1.putConstraint(SpringLayout.NORTH, spinnerHeight, -6, SpringLayout.NORTH, lblHeight);
		sl_panel_1.putConstraint(SpringLayout.NORTH, chckbxOpenGL, 12, SpringLayout.SOUTH, lblHeight);
		sl_panel_1.putConstraint(SpringLayout.WEST, lblHeight, 0, SpringLayout.WEST, chckbxFullscreen);
		panel_1.setLayout(sl_panel_1);
		
		chckbxFullscreen.setForeground(Color.WHITE);
		chckbxFullscreen.setName("Fullscreen");
		chckbxFullscreen.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent e) {
				
				fullscreen = chckbxFullscreen.isSelected();
				
				lblWidth.setEnabled(!fullscreen);
				lblHeight.setEnabled(!fullscreen);
				spinnerWidth.setEnabled(!fullscreen);
				spinnerHeight.setEnabled(!fullscreen);
				lblPx.setEnabled(!fullscreen);
				lblPx2.setEnabled(!fullscreen);
				
			}
		});
		sl_panel_1.putConstraint(SpringLayout.NORTH, chckbxFullscreen, 10, SpringLayout.NORTH, panel_1);
		sl_panel_1.putConstraint(SpringLayout.WEST, chckbxFullscreen, 10, SpringLayout.WEST, panel_1);
		panel_1.add(chckbxFullscreen);
		
		lblWidth.setForeground(Color.WHITE);
		panel_1.add(lblWidth);
		lblWidth.setName("Width");
		
		lblHeight.setForeground(Color.WHITE);
		lblHeight.setName("Height");
		panel_1.add(lblHeight);
		sl_panel_1.putConstraint(SpringLayout.WEST, chckbxOpenGL, 0, SpringLayout.WEST, chckbxFullscreen);
		chckbxOpenGL.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent arg0) {
				opengl = chckbxOpenGL.isSelected();
			}
		});
		chckbxOpenGL.setForeground(Color.WHITE);
		chckbxOpenGL.setName("OpenGL");
		panel_1.add(chckbxOpenGL);
		sl_panel_1.putConstraint(SpringLayout.SOUTH, btnSaveStart, -10, SpringLayout.SOUTH, panel_1);
		sl_panel_1.putConstraint(SpringLayout.EAST, btnSaveStart, 0, SpringLayout.EAST, panel_1);
		sl_panel_1.putConstraint(SpringLayout.SOUTH, btnSave, -10, SpringLayout.SOUTH, panel_1);
		sl_panel_1.putConstraint(SpringLayout.EAST, btnSave, -65, SpringLayout.EAST, panel_1);
		
		btnSave.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseReleased(MouseEvent e) {
				try {
					Settings.SaveFile();
				} catch (IOException f) {
				}
			}
		});
		spinnerHeight.setName("Height");
		
		panel_1.add(spinnerHeight);
		spinnerWidth.setName("Height");
		
		panel_1.add(spinnerWidth);
		lblPx.setForeground(Color.WHITE);
		
		panel_1.add(lblPx);
		lblPx2.setForeground(Color.WHITE);
		
		panel_1.add(lblPx2);
		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING)
						.addComponent(tabbedPane, GroupLayout.PREFERRED_SIZE, 297, GroupLayout.PREFERRED_SIZE)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addComponent(btnSave)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(btnSaveStart)
							.addPreferredGap(ComponentPlacement.RELATED)))
					.addGap(302))
		);
		gl_contentPane.setVerticalGroup(
			gl_contentPane.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addComponent(tabbedPane, GroupLayout.DEFAULT_SIZE, 206, Short.MAX_VALUE)
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(btnSaveStart)
						.addComponent(btnSave))
					.addContainerGap())
		);
		btnSaveStart.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseReleased(MouseEvent arg0) {
				try {
					Settings.SaveFile();
					Game.startGame();
					Configurator.this.dispose();
				} catch (IOException | InterruptedException f) {
				}
			}
		});
		contentPane.setLayout(gl_contentPane);
	}

	static void loadSettings() throws IOException, InterruptedException {
		
		Settings.OpenFile();
		
		int hash = 0;
		
		for(int i = 0; i < Settings.settings.size(); i++) {
			
			if(Settings.settings.get(i).startsWith("fullscreen")) {
				
				try {
					fullscreen = Boolean.parseBoolean(Settings.settings.get(i).split("=")[1]);
				} catch(NumberFormatException e) {
					throw new IOException();
				}
				hash++;
			}
			
			if(Settings.settings.get(i).startsWith("opengl")) {
				try {
					opengl = Boolean.parseBoolean(Settings.settings.get(i).split("=")[1]);
				} catch (NumberFormatException e) {
					throw new IOException();
				}
				hash++;
			}
			
			if(Settings.settings.get(i).startsWith("gameWidth")) {
				try {
					width = Integer.parseInt(Settings.settings.get(i).split("=")[1]);
				} catch (NumberFormatException e) {
					throw new IOException();
				}
				hash++;
			}
			
			if(Settings.settings.get(i).startsWith("gameHeight")) {
				try {
					height = Integer.parseInt(Settings.settings.get(i).split("=")[1]);
				} catch (NumberFormatException e) {
					throw new IOException();
				}
				hash++;
			}
			
		}
		
		if(hash != 4) {
			throw new IOException();
		}
		
	}
}
