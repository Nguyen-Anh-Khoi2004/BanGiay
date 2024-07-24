package raven.application.form;

import com.formdev.flatlaf.FlatClientProperties;
import com.formdev.flatlaf.extras.FlatSVGIcon;
import com.formdev.flatlaf.util.UIScale;
import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.ComponentOrientation;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Insets;
import java.awt.LayoutManager;
import java.awt.event.ActionEvent;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import raven.application.Application;
import raven.application.form.other.FormBanHang;
import raven.application.form.other.FormDoanhThu;
import raven.application.form.other.FormSanPham;
import raven.application.form.other.FormHoaDon;
import raven.application.form.other.FormNhanVien;
import raven.application.form.other.FormKhachHang;
import raven.application.form.other.FormKhuyenMai;
import raven.menu.Menu;
import raven.menu.MenuAction;

/**
 *
 * @author Raven
 */
public class MainForm extends JLayeredPane {

    private JLabel lbUserInfo;
    
    public MainForm() {
        init();
    }

    private void init() {
        setBorder(new EmptyBorder(5, 5, 5, 5));
        setLayout(new MainFormLayout());
        menu = new Menu();
        panelBody = new JPanel(new BorderLayout());
        initMenuArrowIcon();
        menuButton.putClientProperty(FlatClientProperties.STYLE, ""
                + "background:$Menu.button.background;"
                + "arc:999;"
                + "focusWidth:0;"
                + "borderWidth:0");
        menuButton.addActionListener((ActionEvent e) -> {
            setMenuFull(!menu.isMenuFull());
        });
        initMenuEvent();
        setLayer(menuButton, JLayeredPane.POPUP_LAYER);
        add(menuButton);
        add(menu);
        add(panelBody);
        
        lbUserInfo = new JLabel();
        lbUserInfo.setBounds(10, 10, 300, 20); // Đặt vị trí và kích thước cho label
        add(lbUserInfo);
    }
    public void updateUserInfo(String username, String role) {
        lbUserInfo.setText("Tên đăng nhập: " + username + " | Vai trò: " + role);
    }

    public void hideMenuItemsExcept(String[] formNames) {
        menu.hideItemsExcept(formNames);
    }

    @Override
    public void applyComponentOrientation(ComponentOrientation o) {
        super.applyComponentOrientation(o);
        initMenuArrowIcon();
    }

    private void initMenuArrowIcon() {
        if (menuButton == null) {
            menuButton = new JButton();
        }
        String icon = (getComponentOrientation().isLeftToRight()) ? "menu_left.svg" : "menu_right.svg";
        menuButton.setIcon(new FlatSVGIcon("raven/icon/svg/" + icon, 0.8f));
    }

    private void initMenuEvent() {
        menu.addMenuEvent((int index, int subIndex, MenuAction action) -> {
            // Application.mainForm.showForm(new DefaultForm("Form : " + index + " " + subIndex));
            if (index == 0) {
                Application.showForm(new FormBanHang());
            } else if (index == 1) {
                Application.showForm(new FormSanPham());
            } else if (index == 2) {
                Application.showForm(new FormNhanVien(LAYER_PROPERTY));
            } else if (index == 3) {
                Application.showForm(new FormKhachHang(LAYER_PROPERTY));
            } else if (index == 4) {
                Application.showForm(new FormHoaDon(LAYER_PROPERTY));
            } else if (index == 5) {
                Application.showForm(new FormKhuyenMai(LAYER_PROPERTY));
            } else if (index == 6) {
                Application.showForm(new FormDoanhThu(LAYER_PROPERTY));
            } else if (index == 7) {
                Application.logout();
            } else {
                action.cancel();
            }
        });
    }

    private void initMenuEvent1() {
        menu.addMenuEvent((int index, int subIndex, MenuAction action) -> {
            if (index == 0) {
                Application.showForm(new FormBanHang());
            } else if (index == 3) {
                Application.showForm(new FormKhachHang(LAYER_PROPERTY));
            } else if (index == 4) {
                Application.showForm(new FormHoaDon(LAYER_PROPERTY));
            } else {
                action.cancel();
            }
        });
    }

    public void setPermission(int vaiTro) {
        if (vaiTro == 0) {
            menu.hideItemsExcept(new String[]{"Bán hàng", "Khách Hàng","Sản phẩm", "Hóa đơn", "Logout"});
            // Ẩn các mục menu không phù hợp với vai trò 0
        } else {
            // Hiển thị tất cả các mục menu cho vai trò khác
            menu.showAllItems();
        }
    }

    private void setMenuFull(boolean full) {
        String icon;
        if (getComponentOrientation().isLeftToRight()) {
            icon = (full) ? "menu_left.svg" : "menu_right.svg";
        } else {
            icon = (full) ? "menu_right.svg" : "menu_left.svg";
        }
        menuButton.setIcon(new FlatSVGIcon("raven/icon/svg/" + icon, 0.8f));
        menu.setMenuFull(full);
        revalidate();
    }

    public void hideMenu() {
        menu.hideMenuItem();
    }

    public void showForm(Component component) {
        panelBody.removeAll();
        panelBody.add(component);
        panelBody.repaint();
        panelBody.revalidate();
    }

    public void setSelectedMenu(int index, int subIndex) {
        menu.setSelectedMenu(index, subIndex);
    }

    private Menu menu;
    private JPanel panelBody;
    private JButton menuButton;

    private class MainFormLayout implements LayoutManager {

        @Override
        public void addLayoutComponent(String name, Component comp) {
        }

        @Override
        public void removeLayoutComponent(Component comp) {
        }

        @Override
        public Dimension preferredLayoutSize(Container parent) {
            synchronized (parent.getTreeLock()) {
                return new Dimension(5, 5);
            }
        }

        @Override
        public Dimension minimumLayoutSize(Container parent) {
            synchronized (parent.getTreeLock()) {
                return new Dimension(0, 0);
            }
        }

        @Override
        public void layoutContainer(Container parent) {
            synchronized (parent.getTreeLock()) {
                boolean ltr = parent.getComponentOrientation().isLeftToRight();
                Insets insets = UIScale.scale(parent.getInsets());
                int x = insets.left;
                int y = insets.top;
                int width = parent.getWidth() - (insets.left + insets.right);
                int height = parent.getHeight() - (insets.top + insets.bottom);
                int menuWidth = UIScale.scale(menu.isMenuFull() ? menu.getMenuMaxWidth() : menu.getMenuMinWidth());
                int menuX = ltr ? x : x + width - menuWidth;
                menu.setBounds(menuX, y, menuWidth, height);
                int menuButtonWidth = menuButton.getPreferredSize().width;
                int menuButtonHeight = menuButton.getPreferredSize().height;
                int menubX;
                if (ltr) {
                    menubX = (int) (x + menuWidth - (menuButtonWidth * (menu.isMenuFull() ? 0.5f : 0.3f)));
                } else {
                    menubX = (int) (menuX - (menuButtonWidth * (menu.isMenuFull() ? 0.5f : 0.7f)));
                }
                menuButton.setBounds(menubX, UIScale.scale(30), menuButtonWidth, menuButtonHeight);
                int gap = UIScale.scale(5);
                int bodyWidth = width - menuWidth - gap;
                int bodyHeight = height;
                int bodyx = ltr ? (x + menuWidth + gap) : x;
                int bodyy = y;
                panelBody.setBounds(bodyx, bodyy, bodyWidth, bodyHeight);
            }
        }
    }
}