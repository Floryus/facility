package gui;

import java.awt.BorderLayout;
import java.util.ArrayList;
import java.util.Collections;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTree;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;
import javax.swing.tree.TreePath;

import global.GlobalVerwaltung;
import maintainables.Building;
import maintainables.Equipment;
import maintainables.Level;
import maintainables.Room;

/*
 * Das BuildingManagementPanel ist das Panel, das die Gebäudeverwaltung enthält.
 * Über dieses lassen sich die Gebäude, Etagen, Räume und Equipment verwalten.
 * Ebenso lassen sich hier die Tickets und Tasks erstellen.
 * 
 * @author Florian Schmidt
 */
public class BuildingManagementPanel extends JPanel {

    private JTree tree;
    private DefaultTreeModel treeModel;

    private DefaultMutableTreeNode selectedNode;

    public BuildingManagementPanel() {

        setLayout(new BorderLayout());
        add(new JLabel("Buildings panel"), BorderLayout.CENTER);
        initComponents();
        buildTree();
        buildButtonPanel();
    }

    private void initComponents() {
        setLayout(new BorderLayout());

        tree = new JTree();
        JScrollPane scrollPane = new JScrollPane(tree);

        add(scrollPane, BorderLayout.CENTER);

        // Hinzufügen des TreeSelectionListeners zum JTree
        tree.addTreeSelectionListener(e -> {
            // Erhalten des ausgewählten TreePath
            TreePath selectedPath = tree.getSelectionPath();
            if (selectedPath != null) {
                // Erhalten des ausgewählten Knotens
                selectedNode = (DefaultMutableTreeNode) selectedPath.getLastPathComponent();

                // Logik falls "New"-Knoten ausgewählt ist
                if (selectedNode.getUserObject() instanceof AddTreeNode) {
                    AddTreeNode TreeNode = (AddTreeNode) selectedNode.getUserObject();
                    if (TreeNode.building != null) {
                        // createLevel - maxRooms
                        AddLevelPopup addLevelPopup = new AddLevelPopup(this, TreeNode.building);
                        addLevelPopup.setVisible(true);
                    } else if (TreeNode.level != null) {
                        // createRoom - Room Types Enum
                        AddRoomPopup addRoomPopup = new AddRoomPopup(this, TreeNode.level);
                        addRoomPopup.setVisible(true);
                    } else if (TreeNode.room != null) {
                        // createEquipment - name, description, price, warranty, purchaseDate
                        AddEquipmentPopup addEquipmentPopup = new AddEquipmentPopup(this,
                                TreeNode.room);
                        addEquipmentPopup.setVisible(true);
                    } else {
                        // createBuilding - name, maxLevels, address
                        AddBuildingPopup addBuildingPopup = new AddBuildingPopup(this);
                        addBuildingPopup.setVisible(true);
                    }
                }
            }
        });
    }

    private void buildButtonPanel() {
        JButton ticketButton = new JButton("Ticket erstellen");
        ticketButton.addActionListener(e -> {
            TicketCreateDialog ticketCreateDialog = new TicketCreateDialog(selectedNode.getUserObject());
            ticketCreateDialog.setVisible(true);
        });

        JButton taskButton = new JButton("Wartungsaufgabe erstellen");
        taskButton.addActionListener(e -> {
            MaintenanceTaskDialog taskCreateDialog = new MaintenanceTaskDialog(selectedNode.getUserObject());
            taskCreateDialog.setVisible(true);
        });

        JPanel buttonPanel = new JPanel();
        buttonPanel.add(ticketButton);
        buttonPanel.add(taskButton);
        add(buttonPanel, BorderLayout.SOUTH);
    }

    private void buildTree() {

        // Wurzelknoten
        DefaultMutableTreeNode root = new DefaultMutableTreeNode("Alle Gebäude");

        // Lade alle Gebäude und sortiere sie
        ArrayList<Building> buildings = GlobalVerwaltung.getBuildingVerwaltung().getBuildings();
        Collections.sort(buildings);

        for (Building building : buildings) {
            BuildingTreeNode buildingNode = new BuildingTreeNode(building);
            DefaultMutableTreeNode buildingTreeNode = new DefaultMutableTreeNode(buildingNode);
            root.add(buildingTreeNode);

            // Füge Etage hinzu
            ArrayList<Level> levels = building.getLevels();
            Collections.sort(levels);
            for (Level level : levels) {
                LevelTreeNode levelNode = new LevelTreeNode(level);
                DefaultMutableTreeNode levelTreeNode = new DefaultMutableTreeNode(levelNode);
                buildingTreeNode.add(levelTreeNode);

                // Füge Räume hinzu
                ArrayList<Room> rooms = level.getRooms();
                Collections.sort(rooms);
                for (Room room : rooms) {
                    RoomTreeNode roomNode = new RoomTreeNode(room);
                    DefaultMutableTreeNode roomTreeNode = new DefaultMutableTreeNode(roomNode);
                    levelTreeNode.add(roomTreeNode);

                    // Füge Equipment hinzu
                    ArrayList<Equipment> equipments = room.getEquipment();
                    Collections.sort(equipments);
                    for (Equipment equip : equipments) {
                        EquipmentTreeNode equipmentNode = new EquipmentTreeNode(equip);
                        DefaultMutableTreeNode equipmentTreeNode = new DefaultMutableTreeNode(equipmentNode);
                        roomTreeNode.add(equipmentTreeNode);
                    }
                    AddTreeNode addNode = new AddTreeNode(room);
                    DefaultMutableTreeNode addTreeNode = new DefaultMutableTreeNode(addNode);
                    roomTreeNode.add(addTreeNode);

                }
                if (level.getRooms().size() < level.getMaxRooms()) {
                    AddTreeNode addNode = new AddTreeNode(level);
                    DefaultMutableTreeNode addTreeNode = new DefaultMutableTreeNode(addNode);
                    levelTreeNode.add(addTreeNode);
                }

            }
            if (building.getLevels().size() < building.getMaxLevels()) {
                AddTreeNode addNode = new AddTreeNode(building);
                DefaultMutableTreeNode addTreeNode = new DefaultMutableTreeNode(addNode);
                buildingTreeNode.add(addTreeNode);
            }
        }
        AddTreeNode addNode = new AddTreeNode();
        DefaultMutableTreeNode addTreeNode = new DefaultMutableTreeNode(addNode);
        root.add(addTreeNode);

        // Erstelle das TreeModel mit dem Wurzelknoten
        treeModel = new DefaultTreeModel(root);
        tree.setModel(treeModel);

    }

    public void reloadTree() {
        buildTree();
    }

    public class BuildingTreeNode {
        private Building building;

        public BuildingTreeNode(Building building) {
            this.building = building;
        }

        public Building getBuilding() {
            return building;
        }

        @Override
        public String toString() {
            return building.getName();
        }
    }

    public class LevelTreeNode {
        private Level level;

        public LevelTreeNode(Level level) {
            this.level = level;
        }

        public Level getLevel() {
            return level;
        }

        @Override
        public String toString() {
            return "Level " + String.valueOf(level.getNumber());
        }
    }

    public class RoomTreeNode {
        private Room room;

        public RoomTreeNode(Room room) {
            this.room = room;
        }

        public Room getRoom() {
            return room;
        }

        @Override
        public String toString() {
            return "Raum " + String.valueOf(room.getName());
        }
    }

    public class EquipmentTreeNode {
        private Equipment equipment;

        public EquipmentTreeNode(Equipment equipment) {
            this.equipment = equipment;
        }

        public Equipment getEquipment() {
            return equipment;
        }

        @Override
        public String toString() {
            return String.valueOf(equipment.getName());
        }
    }

    public class AddTreeNode {

        private Building building;
        private Level level;
        private Room room;
        private Equipment equipment;

        // For create Building
        public AddTreeNode() {
        }

        // For create level
        public AddTreeNode(Building building) {
            this.building = building;
        }

        // For create room
        public AddTreeNode(Level level) {
            this.level = level;
        }

        // For create equipment
        public AddTreeNode(Room room) {
            this.room = room;
        }

        @Override
        public String toString() {
            return "+ Hinzufügen";
        }
    }

}
