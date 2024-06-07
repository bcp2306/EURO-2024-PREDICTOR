import java.awt.Component;
import java.awt.Dimension;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.ListCellRenderer;

class TeamItem {
    private String name;
    private ImageIcon icon;

    public TeamItem(String name, ImageIcon icon) {
        this.name = name;
        this.icon = icon;
    }

    public String getName() {
        return name;
    }

    public ImageIcon getIcon() {
        return icon;
    }

    @Override
    public String toString() {
        return name;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        TeamItem teamItem = (TeamItem) obj;
        return name.equals(teamItem.name);
    }

    @Override
    public int hashCode() {
        return name.hashCode();
    }
}

class TeamItemRenderer extends JLabel implements ListCellRenderer<TeamItem> {
    @Override
    public Component getListCellRendererComponent(JList<? extends TeamItem> list, TeamItem value, int index, boolean isSelected, boolean cellHasFocus) {
        setText(value.getName());
        setIcon(value.getIcon());

        if (isSelected) {
            setBackground(list.getSelectionBackground());
            setForeground(list.getSelectionForeground());
        } else {
            setBackground(list.getBackground());
            setForeground(list.getForeground());
        }

        setPreferredSize(new Dimension(300, 50)); 
        setOpaque(true);
        return this;
    }
}

class Team {
    private String name;
    private int strength;

    public Team(String name, int strength) {
        this.name = name;
        this.strength = strength;
    }

    public String getName() {
        return name;
    }

    public int getStrength() {
        return strength;
    }
}
