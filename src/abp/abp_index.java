package abp;

import java.sql.CallableStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

public class abp_index extends javax.swing.JFrame {

    ConexionBD con;
    ResultSet datosbase;
    DefaultTableModel model;
    DefaultComboBoxModel modelo = new DefaultComboBoxModel();
    CallableStatement rs = null;
    String[] e_pr = {"codigo", " nombre", "cantidad", "tipo", "descripcion"};
    String[] e_p = {"codigo", "Pueblo", "Nombre de la calle"};
    String[] e_dir = {"codigo", "Descripcion"};
    String[] e_emp = {"codigo", "nombre ", "ap", " am", "edad", "puesto"};
    String[] e_vent = {"codigo", "producto", "cantidad", "direciones", "", "empleado", "", ""};
    String dte[] = new String[50];
    String dtps[] = new String[50];
    String dtpst[] = new String[50];
    String dtem[] = new String[50];
    String dtven[] = new String[50];

    public abp_index() {
        initComponents();
        setLocationRelativeTo(null);
        setResizable(false);
        con = new ConexionBD();
        cargar_emp();
        cargar_pro();
        cargar_put();
        cargar_dir();
        cargar_vent();
        combos();
        String cta = "";
        for (int i = 17; i < 46; i++) {
            e_edad.addItem("" + i);
        }
        p_1.setVisible(true);
        p_2.setVisible(false);
        p_3.setVisible(false);
        p_4.setVisible(false);
        p_5.setVisible(false);
        
    }
    public void combos(){
        try {
            datosbase = con.consultar("select * from tipos");
            while (datosbase.next()) {
                pc_tipos.addItem(datosbase.getString("descripcion"));
            }
        } catch (SQLException e) {
            System.out.println(""+e);
        }
        try {
            datosbase = con.consultar("select * from puestos");
            while (datosbase.next()) {
                e_puesto.addItem(datosbase.getString("puesto"));
            }
        } catch (SQLException e) {
            System.out.println(""+e);
        }
    }

    public void cargar_emp() {
        model = new DefaultTableModel(null, e_emp);
        String sql = "select personal.id,nombre ,ap, am,edad,puesto from personal ,puestos where personal.id_puesto=puestos.id";
        try {
            datosbase = con.consultar(sql);
            while (datosbase.next()) {
                dte[0] = "" + datosbase.getString("id");
                dte[1] = "" + datosbase.getString("nombre");
                dte[2] = "" + datosbase.getString("ap");
                dte[3] = "" + datosbase.getString("am");
                dte[4] = "" + datosbase.getString("edad");
                dte[5] = "" + datosbase.getString("puesto");
                model.addRow(dte);
            }
            t_empleados.setModel(model);
        } catch (SQLException e) {
            JOptionPane.showInputDialog(null, e);
        }

    }
    public void cargar_pro() {
        model = new DefaultTableModel(null, e_pr);
        String sql = "select productos.id,nombre,cantidad,tipos.descripcion ,productos.descripcion from productos,tipos where productos.id_tipo=tipos.id";
        try {
            datosbase = con.consultar(sql);
            while (datosbase.next()) {
                dte[0] = "" + datosbase.getString("id");
                dte[1] = "" + datosbase.getString("nombre");
                dte[2] = "" + datosbase.getString("cantidad");
                dte[3] = "" + datosbase.getString("tipos.descripcion");
                dte[4] = "" + datosbase.getString("productos.descripcion");
                model.addRow(dte);
            }

            t_pro.setModel(model);
        } catch (SQLException e) {
            JOptionPane.showInputDialog(null, e);
        }
        

    }
    public void cargar_dir() {
        model = new DefaultTableModel(null, e_p);
        String sql = "select * from direciones";
        try {
            datosbase = con.consultar(sql);
            while (datosbase.next()) {
                dtps[0] = "" + datosbase.getString("id");
                dtps[1] = "" + datosbase.getString("pueblo");
                dtps[2] = "" + datosbase.getString("calle");
                model.addRow(dtps);
            }
            t_dir.setModel(model);
        } catch (SQLException e) {
            JOptionPane.showInputDialog(null, e);
        }
    }
    public void cargar_put() {
        model = new DefaultTableModel(null, e_dir);
        String sql = "select * from puestos";
        try {
            datosbase = con.consultar(sql);
            while (datosbase.next()) {
                dtpst[0] = "" + datosbase.getString("id");
                dtpst[1] = "" + datosbase.getString("puesto");
                model.addRow(dtpst);
            }

            t_puestos.setModel(model);
        } catch (SQLException e) {
            JOptionPane.showInputDialog(null, e);
        }
    }
    public void cargar_vent() {
        model = new DefaultTableModel(null, e_vent);
        String sql = "select ventas.id,productos.nombre,ventas.cantidad,direciones.pueblo,"
                + "direciones.calle,personal.nombre,personal.am,personal.ap  "
                + "from ventas,personal,direciones,productos  "
                + "where ventas.id_persona=personal.id "
                + "and ventas.direccion=direciones.id "
                + "and ventas.id_producto=productos.id";
        try {
            datosbase = con.consultar(sql);
            while (datosbase.next()) {
                dtven[0] = "" + datosbase.getString("ventas.id");
                dtven[1] = "" + datosbase.getString("productos.nombre");
                dtven[2] = "" + datosbase.getString("ventas.cantidad");
                dtven[3] = "" + datosbase.getString("direciones.pueblo");
                dtven[4] = "" + datosbase.getString("direciones.calle");
                dtven[5] = "" + datosbase.getString("personal.nombre");
                dtven[6] = "" + datosbase.getString("personal.am");
                dtven[7] = "" + datosbase.getString("personal.ap");
                model.addRow(dtven);
            }

            t_ventas.setModel(model);
        } catch (SQLException e) {
            JOptionPane.showInputDialog(null, e);
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        p_1 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        t_pro = new javax.swing.JTable();
        jButton4 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jButton1 = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        n_emp = new javax.swing.JLabel();
        bus_p = new javax.swing.JTextField();
        p_nom = new javax.swing.JTextField();
        p_can = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        p_des = new javax.swing.JTextField();
        pc_tipos = new javax.swing.JComboBox<>();
        id = new javax.swing.JLabel();
        jLabel31 = new javax.swing.JLabel();
        p_2 = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        t_empleados = new javax.swing.JTable();
        jButton10 = new javax.swing.JButton();
        jButton11 = new javax.swing.JButton();
        jButton12 = new javax.swing.JButton();
        jButton13 = new javax.swing.JButton();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        bus_emp = new javax.swing.JTextField();
        e_nom = new javax.swing.JTextField();
        e_ap = new javax.swing.JTextField();
        jLabel11 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        e_edad = new javax.swing.JComboBox<>();
        id_e = new javax.swing.JLabel();
        jLabel32 = new javax.swing.JLabel();
        jLabel21 = new javax.swing.JLabel();
        e_am = new javax.swing.JTextField();
        e_puesto = new javax.swing.JComboBox<>();
        p_3 = new javax.swing.JPanel();
        jScrollPane3 = new javax.swing.JScrollPane();
        t_ventas = new javax.swing.JTable();
        jButton14 = new javax.swing.JButton();
        jButton15 = new javax.swing.JButton();
        jButton16 = new javax.swing.JButton();
        jButton17 = new javax.swing.JButton();
        jLabel13 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();
        jLabel16 = new javax.swing.JLabel();
        n_emp2 = new javax.swing.JLabel();
        bus_ventas = new javax.swing.JTextField();
        p_nom2 = new javax.swing.JTextField();
        p_can2 = new javax.swing.JTextField();
        jLabel17 = new javax.swing.JLabel();
        jLabel18 = new javax.swing.JLabel();
        p_des2 = new javax.swing.JTextField();
        pc_tipos2 = new javax.swing.JComboBox<>();
        id2 = new javax.swing.JLabel();
        jLabel33 = new javax.swing.JLabel();
        p_4 = new javax.swing.JPanel();
        jScrollPane4 = new javax.swing.JScrollPane();
        t_puestos = new javax.swing.JTable();
        jButton18 = new javax.swing.JButton();
        jButton19 = new javax.swing.JButton();
        jButton20 = new javax.swing.JButton();
        jButton21 = new javax.swing.JButton();
        jLabel19 = new javax.swing.JLabel();
        jLabel20 = new javax.swing.JLabel();
        jLabel22 = new javax.swing.JLabel();
        n_emp3 = new javax.swing.JLabel();
        bus_puestos = new javax.swing.JTextField();
        p_descripcion = new javax.swing.JTextField();
        id_pstt = new javax.swing.JLabel();
        jLabel34 = new javax.swing.JLabel();
        p_5 = new javax.swing.JPanel();
        jScrollPane5 = new javax.swing.JScrollPane();
        t_dir = new javax.swing.JTable();
        jButton22 = new javax.swing.JButton();
        jButton23 = new javax.swing.JButton();
        jButton24 = new javax.swing.JButton();
        jButton25 = new javax.swing.JButton();
        jLabel25 = new javax.swing.JLabel();
        jLabel26 = new javax.swing.JLabel();
        jLabel27 = new javax.swing.JLabel();
        jLabel28 = new javax.swing.JLabel();
        n_emp4 = new javax.swing.JLabel();
        bus_direciones = new javax.swing.JTextField();
        d_pueblo = new javax.swing.JTextField();
        d_calle = new javax.swing.JTextField();
        id_dir = new javax.swing.JLabel();
        jLabel35 = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        jButton5 = new javax.swing.JButton();
        jButton6 = new javax.swing.JButton();
        jButton7 = new javax.swing.JButton();
        jButton8 = new javax.swing.JButton();
        jButton9 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setBackground(new java.awt.Color(0, 0, 0));
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        p_1.setBackground(new java.awt.Color(204, 204, 255));
        p_1.setPreferredSize(new java.awt.Dimension(800, 380));
        p_1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        t_pro.setBackground(new java.awt.Color(204, 204, 204));
        t_pro.setForeground(new java.awt.Color(102, 102, 255));
        t_pro.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        t_pro.setGridColor(new java.awt.Color(51, 102, 255));
        t_pro.setSelectionBackground(new java.awt.Color(204, 204, 255));
        t_pro.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                t_proMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(t_pro);

        p_1.add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 260, 710, 160));

        jButton4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/abp/add.jpg"))); // NOI18N
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });
        p_1.add(jButton4, new org.netbeans.lib.awtextra.AbsoluteConstraints(660, 190, 60, 60));

        jButton3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/abp/actualizar.jpg"))); // NOI18N
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });
        p_1.add(jButton3, new org.netbeans.lib.awtextra.AbsoluteConstraints(660, 130, 60, 60));

        jButton2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/abp/eliminar.jpg"))); // NOI18N
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });
        p_1.add(jButton2, new org.netbeans.lib.awtextra.AbsoluteConstraints(660, 70, 60, 60));

        jButton1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/abp/guardar1.jpg"))); // NOI18N
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        p_1.add(jButton1, new org.netbeans.lib.awtextra.AbsoluteConstraints(660, 10, 60, 60));

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel1.setText("BUSCAR");
        p_1.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(340, 220, 70, 21));

        jLabel4.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel4.setText("Nombre");
        p_1.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 40, 50, 24));

        jLabel5.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel5.setText("Cantidad");
        p_1.add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(230, 40, 50, 24));

        jLabel2.setText("numero");
        p_1.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 140, 0, 26));

        n_emp.setBackground(new java.awt.Color(0, 0, 51));
        p_1.add(n_emp, new org.netbeans.lib.awtextra.AbsoluteConstraints(322, 154, 152, 26));

        bus_p.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bus_pActionPerformed(evt);
            }
        });
        bus_p.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                bus_pKeyPressed(evt);
            }
        });
        p_1.add(bus_p, new org.netbeans.lib.awtextra.AbsoluteConstraints(420, 210, 180, 30));
        p_1.add(p_nom, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 40, 120, 30));
        p_1.add(p_can, new org.netbeans.lib.awtextra.AbsoluteConstraints(290, 40, 60, 30));

        jLabel6.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel6.setText("Tipo");
        p_1.add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(370, 40, 30, 30));

        jLabel3.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel3.setText("Descripcion");
        p_1.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 90, 70, 30));
        p_1.add(p_des, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 90, 510, 30));

        p_1.add(pc_tipos, new org.netbeans.lib.awtextra.AbsoluteConstraints(420, 40, 180, 30));

        id.setText("id");
        p_1.add(id, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 150, 40, 20));

        jLabel31.setFont(new java.awt.Font("Times New Roman", 1, 24)); // NOI18N
        jLabel31.setText("Productos");
        p_1.add(jLabel31, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 4, 200, 30));

        getContentPane().add(p_1, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 10, 750, 430));

        p_2.setBackground(new java.awt.Color(204, 204, 255));
        p_2.setPreferredSize(new java.awt.Dimension(800, 380));
        p_2.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        t_empleados.setBackground(new java.awt.Color(204, 204, 204));
        t_empleados.setForeground(new java.awt.Color(102, 102, 255));
        t_empleados.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        t_empleados.setGridColor(new java.awt.Color(51, 102, 255));
        t_empleados.setSelectionBackground(new java.awt.Color(204, 204, 255));
        t_empleados.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                t_empleadosMouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(t_empleados);

        p_2.add(jScrollPane2, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 260, 710, 160));

        jButton10.setIcon(new javax.swing.ImageIcon(getClass().getResource("/abp/add.jpg"))); // NOI18N
        jButton10.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton10ActionPerformed(evt);
            }
        });
        p_2.add(jButton10, new org.netbeans.lib.awtextra.AbsoluteConstraints(660, 190, 60, 60));

        jButton11.setIcon(new javax.swing.ImageIcon(getClass().getResource("/abp/actualizar.jpg"))); // NOI18N
        jButton11.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton11ActionPerformed(evt);
            }
        });
        p_2.add(jButton11, new org.netbeans.lib.awtextra.AbsoluteConstraints(660, 130, 60, 60));

        jButton12.setIcon(new javax.swing.ImageIcon(getClass().getResource("/abp/eliminar.jpg"))); // NOI18N
        jButton12.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton12ActionPerformed(evt);
            }
        });
        p_2.add(jButton12, new org.netbeans.lib.awtextra.AbsoluteConstraints(660, 70, 60, 60));

        jButton13.setIcon(new javax.swing.ImageIcon(getClass().getResource("/abp/guardar1.jpg"))); // NOI18N
        jButton13.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton13ActionPerformed(evt);
            }
        });
        p_2.add(jButton13, new org.netbeans.lib.awtextra.AbsoluteConstraints(660, 10, 60, 60));

        jLabel7.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel7.setText("BUSCAR");
        p_2.add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(340, 220, 70, 21));

        jLabel8.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel8.setText("Nombre");
        p_2.add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 40, 50, 24));

        jLabel9.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel9.setText("1er Apellido");
        p_2.add(jLabel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(310, 40, 70, 24));

        jLabel10.setText("numero");
        p_2.add(jLabel10, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 140, 0, 26));

        bus_emp.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bus_empActionPerformed(evt);
            }
        });
        bus_emp.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                bus_empKeyPressed(evt);
            }
        });
        p_2.add(bus_emp, new org.netbeans.lib.awtextra.AbsoluteConstraints(420, 210, 180, 30));
        p_2.add(e_nom, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 40, 200, 30));
        p_2.add(e_ap, new org.netbeans.lib.awtextra.AbsoluteConstraints(400, 40, 200, 30));

        jLabel11.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel11.setText("Puesto");
        p_2.add(jLabel11, new org.netbeans.lib.awtextra.AbsoluteConstraints(250, 140, 60, 30));

        jLabel12.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel12.setText("Edad");
        p_2.add(jLabel12, new org.netbeans.lib.awtextra.AbsoluteConstraints(310, 90, 70, 30));

        p_2.add(e_edad, new org.netbeans.lib.awtextra.AbsoluteConstraints(400, 90, 200, 30));

        id_e.setText("id");
        p_2.add(id_e, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 150, 40, 20));

        jLabel32.setFont(new java.awt.Font("Times New Roman", 1, 24)); // NOI18N
        jLabel32.setText("Empleados");
        p_2.add(jLabel32, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 4, 200, 30));

        jLabel21.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel21.setText("2do Apellido");
        p_2.add(jLabel21, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 90, 70, 30));
        p_2.add(e_am, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 90, 200, 30));

        p_2.add(e_puesto, new org.netbeans.lib.awtextra.AbsoluteConstraints(310, 140, 180, 30));

        getContentPane().add(p_2, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 10, 750, 430));

        p_3.setBackground(new java.awt.Color(204, 204, 255));
        p_3.setPreferredSize(new java.awt.Dimension(800, 380));
        p_3.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        t_ventas.setBackground(new java.awt.Color(204, 204, 204));
        t_ventas.setForeground(new java.awt.Color(102, 102, 255));
        t_ventas.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        t_ventas.setGridColor(new java.awt.Color(51, 102, 255));
        t_ventas.setSelectionBackground(new java.awt.Color(204, 204, 255));
        t_ventas.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                t_ventasMouseClicked(evt);
            }
        });
        jScrollPane3.setViewportView(t_ventas);

        p_3.add(jScrollPane3, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 260, 710, 160));

        jButton14.setIcon(new javax.swing.ImageIcon(getClass().getResource("/abp/add.jpg"))); // NOI18N
        jButton14.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton14ActionPerformed(evt);
            }
        });
        p_3.add(jButton14, new org.netbeans.lib.awtextra.AbsoluteConstraints(660, 190, 60, 60));

        jButton15.setIcon(new javax.swing.ImageIcon(getClass().getResource("/abp/actualizar.jpg"))); // NOI18N
        jButton15.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton15ActionPerformed(evt);
            }
        });
        p_3.add(jButton15, new org.netbeans.lib.awtextra.AbsoluteConstraints(660, 130, 60, 60));

        jButton16.setIcon(new javax.swing.ImageIcon(getClass().getResource("/abp/eliminar.jpg"))); // NOI18N
        jButton16.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton16ActionPerformed(evt);
            }
        });
        p_3.add(jButton16, new org.netbeans.lib.awtextra.AbsoluteConstraints(660, 70, 60, 60));

        jButton17.setIcon(new javax.swing.ImageIcon(getClass().getResource("/abp/guardar1.jpg"))); // NOI18N
        jButton17.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton17ActionPerformed(evt);
            }
        });
        p_3.add(jButton17, new org.netbeans.lib.awtextra.AbsoluteConstraints(660, 10, 60, 60));

        jLabel13.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel13.setText("BUSCAR");
        p_3.add(jLabel13, new org.netbeans.lib.awtextra.AbsoluteConstraints(340, 220, 70, 21));

        jLabel14.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel14.setText("Nombre");
        p_3.add(jLabel14, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 40, 50, 24));

        jLabel15.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel15.setText("Cantidad");
        p_3.add(jLabel15, new org.netbeans.lib.awtextra.AbsoluteConstraints(230, 40, 50, 24));

        jLabel16.setText("numero");
        p_3.add(jLabel16, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 140, 0, 26));

        n_emp2.setBackground(new java.awt.Color(0, 0, 51));
        p_3.add(n_emp2, new org.netbeans.lib.awtextra.AbsoluteConstraints(322, 154, 152, 26));

        bus_ventas.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bus_ventasActionPerformed(evt);
            }
        });
        bus_ventas.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                bus_ventasKeyPressed(evt);
            }
        });
        p_3.add(bus_ventas, new org.netbeans.lib.awtextra.AbsoluteConstraints(420, 210, 180, 30));
        p_3.add(p_nom2, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 40, 120, 30));
        p_3.add(p_can2, new org.netbeans.lib.awtextra.AbsoluteConstraints(290, 40, 60, 30));

        jLabel17.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel17.setText("Tipo");
        p_3.add(jLabel17, new org.netbeans.lib.awtextra.AbsoluteConstraints(370, 40, 30, 30));

        jLabel18.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel18.setText("Descripcion");
        p_3.add(jLabel18, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 90, 70, 30));
        p_3.add(p_des2, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 90, 510, 30));

        p_3.add(pc_tipos2, new org.netbeans.lib.awtextra.AbsoluteConstraints(420, 20, 180, 30));

        id2.setText("id");
        p_3.add(id2, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 150, 40, 20));

        jLabel33.setFont(new java.awt.Font("Times New Roman", 1, 24)); // NOI18N
        jLabel33.setText("Ventas");
        p_3.add(jLabel33, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 4, 200, 30));

        getContentPane().add(p_3, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 10, 750, 430));

        p_4.setBackground(new java.awt.Color(204, 204, 255));
        p_4.setPreferredSize(new java.awt.Dimension(800, 380));
        p_4.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        t_puestos.setBackground(new java.awt.Color(204, 204, 204));
        t_puestos.setForeground(new java.awt.Color(102, 102, 255));
        t_puestos.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        t_puestos.setGridColor(new java.awt.Color(51, 102, 255));
        t_puestos.setSelectionBackground(new java.awt.Color(204, 204, 255));
        t_puestos.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                t_puestosMouseClicked(evt);
            }
        });
        jScrollPane4.setViewportView(t_puestos);

        p_4.add(jScrollPane4, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 260, 710, 160));

        jButton18.setIcon(new javax.swing.ImageIcon(getClass().getResource("/abp/add.jpg"))); // NOI18N
        jButton18.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton18ActionPerformed(evt);
            }
        });
        p_4.add(jButton18, new org.netbeans.lib.awtextra.AbsoluteConstraints(660, 190, 60, 60));

        jButton19.setIcon(new javax.swing.ImageIcon(getClass().getResource("/abp/actualizar.jpg"))); // NOI18N
        jButton19.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton19ActionPerformed(evt);
            }
        });
        p_4.add(jButton19, new org.netbeans.lib.awtextra.AbsoluteConstraints(660, 130, 60, 60));

        jButton20.setIcon(new javax.swing.ImageIcon(getClass().getResource("/abp/eliminar.jpg"))); // NOI18N
        jButton20.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton20ActionPerformed(evt);
            }
        });
        p_4.add(jButton20, new org.netbeans.lib.awtextra.AbsoluteConstraints(660, 70, 60, 60));

        jButton21.setIcon(new javax.swing.ImageIcon(getClass().getResource("/abp/guardar1.jpg"))); // NOI18N
        jButton21.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton21ActionPerformed(evt);
            }
        });
        p_4.add(jButton21, new org.netbeans.lib.awtextra.AbsoluteConstraints(660, 10, 60, 60));

        jLabel19.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel19.setText("BUSCAR");
        p_4.add(jLabel19, new org.netbeans.lib.awtextra.AbsoluteConstraints(340, 220, 70, 21));

        jLabel20.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel20.setText("Descripcion");
        p_4.add(jLabel20, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 60, 80, 24));

        jLabel22.setText("numero");
        p_4.add(jLabel22, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 140, 0, 26));

        n_emp3.setBackground(new java.awt.Color(0, 0, 51));
        p_4.add(n_emp3, new org.netbeans.lib.awtextra.AbsoluteConstraints(322, 154, 152, 26));

        bus_puestos.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bus_puestosActionPerformed(evt);
            }
        });
        bus_puestos.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                bus_puestosKeyPressed(evt);
            }
        });
        p_4.add(bus_puestos, new org.netbeans.lib.awtextra.AbsoluteConstraints(420, 210, 180, 30));
        p_4.add(p_descripcion, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 60, 180, 30));

        id_pstt.setText("id");
        p_4.add(id_pstt, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 150, 40, 20));

        jLabel34.setFont(new java.awt.Font("Times New Roman", 1, 24)); // NOI18N
        jLabel34.setText("Puestos");
        p_4.add(jLabel34, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 4, 200, 30));

        getContentPane().add(p_4, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 10, 750, 430));

        p_5.setBackground(new java.awt.Color(204, 204, 255));
        p_5.setPreferredSize(new java.awt.Dimension(800, 380));
        p_5.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        t_dir.setBackground(new java.awt.Color(204, 204, 204));
        t_dir.setForeground(new java.awt.Color(102, 102, 255));
        t_dir.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        t_dir.setGridColor(new java.awt.Color(51, 102, 255));
        t_dir.setSelectionBackground(new java.awt.Color(204, 204, 255));
        t_dir.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                t_dirMouseClicked(evt);
            }
        });
        jScrollPane5.setViewportView(t_dir);

        p_5.add(jScrollPane5, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 260, 710, 160));

        jButton22.setIcon(new javax.swing.ImageIcon(getClass().getResource("/abp/add.jpg"))); // NOI18N
        jButton22.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton22ActionPerformed(evt);
            }
        });
        p_5.add(jButton22, new org.netbeans.lib.awtextra.AbsoluteConstraints(660, 190, 60, 60));

        jButton23.setIcon(new javax.swing.ImageIcon(getClass().getResource("/abp/actualizar.jpg"))); // NOI18N
        jButton23.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton23ActionPerformed(evt);
            }
        });
        p_5.add(jButton23, new org.netbeans.lib.awtextra.AbsoluteConstraints(660, 130, 60, 60));

        jButton24.setIcon(new javax.swing.ImageIcon(getClass().getResource("/abp/eliminar.jpg"))); // NOI18N
        jButton24.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton24ActionPerformed(evt);
            }
        });
        p_5.add(jButton24, new org.netbeans.lib.awtextra.AbsoluteConstraints(660, 70, 60, 60));

        jButton25.setIcon(new javax.swing.ImageIcon(getClass().getResource("/abp/guardar1.jpg"))); // NOI18N
        jButton25.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton25ActionPerformed(evt);
            }
        });
        p_5.add(jButton25, new org.netbeans.lib.awtextra.AbsoluteConstraints(660, 10, 60, 60));

        jLabel25.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel25.setText("BUSCAR");
        p_5.add(jLabel25, new org.netbeans.lib.awtextra.AbsoluteConstraints(340, 220, 70, 21));

        jLabel26.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel26.setText("Pueblo");
        p_5.add(jLabel26, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 80, 50, 24));

        jLabel27.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel27.setText("Calle");
        p_5.add(jLabel27, new org.netbeans.lib.awtextra.AbsoluteConstraints(280, 80, 50, 24));

        jLabel28.setText("numero");
        p_5.add(jLabel28, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 140, 0, 26));

        n_emp4.setBackground(new java.awt.Color(0, 0, 51));
        p_5.add(n_emp4, new org.netbeans.lib.awtextra.AbsoluteConstraints(322, 154, 152, 26));

        bus_direciones.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bus_direcionesActionPerformed(evt);
            }
        });
        bus_direciones.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                bus_direcionesKeyPressed(evt);
            }
        });
        p_5.add(bus_direciones, new org.netbeans.lib.awtextra.AbsoluteConstraints(420, 210, 180, 30));
        p_5.add(d_pueblo, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 80, 120, 30));
        p_5.add(d_calle, new org.netbeans.lib.awtextra.AbsoluteConstraints(360, 80, 190, 30));

        id_dir.setText("id");
        p_5.add(id_dir, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 150, 40, 20));

        jLabel35.setFont(new java.awt.Font("Times New Roman", 1, 24)); // NOI18N
        jLabel35.setText("Direcciones");
        p_5.add(jLabel35, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 4, 200, 30));

        getContentPane().add(p_5, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 10, 750, 430));

        jPanel1.setBackground(new java.awt.Color(153, 204, 255));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jButton5.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jButton5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/abp/empleados.png"))); // NOI18N
        jButton5.setText("Empleados");
        jButton5.setMargin(new java.awt.Insets(2, 0, 2, 20));
        jButton5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton5ActionPerformed(evt);
            }
        });
        jPanel1.add(jButton5, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 80, 180, 60));

        jButton6.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jButton6.setIcon(new javax.swing.ImageIcon(getClass().getResource("/abp/productos.jpg"))); // NOI18N
        jButton6.setText("Productos");
        jButton6.setMargin(new java.awt.Insets(2, 0, 2, 25));
        jButton6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton6ActionPerformed(evt);
            }
        });
        jPanel1.add(jButton6, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 20, 180, 60));

        jButton7.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jButton7.setIcon(new javax.swing.ImageIcon(getClass().getResource("/abp/direciones.jpg"))); // NOI18N
        jButton7.setText("Direcciones");
        jButton7.setMargin(new java.awt.Insets(2, 0, 2, 16));
        jButton7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton7ActionPerformed(evt);
            }
        });
        jPanel1.add(jButton7, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 260, 180, 60));

        jButton8.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jButton8.setIcon(new javax.swing.ImageIcon(getClass().getResource("/abp/ventas.png"))); // NOI18N
        jButton8.setText("Ventas");
        jButton8.setMargin(new java.awt.Insets(2, 0, 2, 42));
        jButton8.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton8ActionPerformed(evt);
            }
        });
        jPanel1.add(jButton8, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 140, 180, 60));

        jButton9.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jButton9.setIcon(new javax.swing.ImageIcon(getClass().getResource("/abp/puestos.png"))); // NOI18N
        jButton9.setText("Puestos");
        jButton9.setMargin(new java.awt.Insets(2, 0, 2, 36));
        jButton9.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton9ActionPerformed(evt);
            }
        });
        jPanel1.add(jButton9, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 200, 180, 60));

        getContentPane().add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 10, 180, 430));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        String nom_p, cantidad_p, tipo_p, desc_p;
        nom_p = p_nom.getText();
        cantidad_p = p_can.getText();
        tipo_p = pc_tipos.getSelectedItem().toString();
        desc_p = p_des.getText();

        if (nom_p.equals("")) {
            JOptionPane.showMessageDialog(null, "es nesesaio insertar nombre");
        } else {
            if (cantidad_p.equals("")) {
                JOptionPane.showMessageDialog(null, "es nesesaio insertar cantidad");
            } else {
                if (desc_p.equals("")) {
                    JOptionPane.showMessageDialog(null, "es nesesaio insertar descripcion");
                } else {
                    String sql = "call inserta_productos('" + nom_p + "'," + cantidad_p + ",'" + tipo_p + "','" + desc_p + "')";
                    try {
                        con.operacion(sql);
                        JOptionPane.showMessageDialog(null, "ingresado correctamente");
                        nom_p = "";
                        cantidad_p = "";
                        tipo_p = "";
                        desc_p = "";
                        cargar_pro();
                    } catch (Exception ex) {
                        System.out.println(ex);
                        JOptionPane.showMessageDialog(null, "ha ocurrido un error al ingresar");
                        nom_p = "";
                        cantidad_p = "";
                        tipo_p = "";
                        desc_p = "";
                    }
                }
            }
        }
    }//GEN-LAST:event_jButton1ActionPerformed
    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed

        String id_p = id.getText();
        if (id_p == "") {
            JOptionPane.showMessageDialog(null, "No ha selecionado un procuto dentro del registro");
        } else {
            String sql = "delete from productos where id=" + id_p + "";
            try {
                con.operacion(sql);
                cargar_pro();
                JOptionPane.showMessageDialog(null, "eliminado correctamente");
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(null, "error");
            }
        }
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        String id_p, nom_p, cantidad_p, tipo_p, desc_p;
        id_p = id.getText();
        nom_p = p_nom.getText();
        cantidad_p = p_can.getText();
        tipo_p = pc_tipos.getSelectedItem().toString();
        desc_p = p_des.getText();
        if (id_p == "") {
            JOptionPane.showMessageDialog(null, "No ha selecionado un procuto dentro del registro");
        } else {
            if (nom_p.equals("")) {
                JOptionPane.showMessageDialog(null, "es nesesaio insertar nombre");
            } else {
                if (cantidad_p.equals("")) {
                    JOptionPane.showMessageDialog(null, "es nesesaio insertar cantidad");
                } else {
                    if (desc_p.equals("")) {
                        JOptionPane.showMessageDialog(null, "es nesesaio insertar descripcion");
                    } else {
                        String sql = "call actualiza_productos(" + id_p + ",'" + nom_p + "'," + cantidad_p + ",'" + tipo_p + "','" + desc_p + "')";
                        try {
                            con.operacion(sql);
                            JOptionPane.showMessageDialog(null, "Actualizado correctamente");
                            cargar_pro();
                            nom_p = "";
                            cantidad_p = "";
                            tipo_p = "";
                            desc_p = "";
                        } catch (Exception ex) {
                            System.out.println(ex);
                            JOptionPane.showMessageDialog(null, "ha ocurrido un error al actualizar");
                            nom_p = "";
                            cantidad_p = "";
                            tipo_p = "";
                            desc_p = "";
                        }
                    }
                }
            }
        }
    }//GEN-LAST:event_jButton3ActionPerformed
    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
        p_can.setText("");
        p_nom.setText("");
        p_des.setText("");
    }//GEN-LAST:event_jButton4ActionPerformed
    private void t_proMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_t_proMouseClicked
        int col = t_pro.getSelectedRow();
        id.setText(t_pro.getModel().getValueAt(col, 0).toString());
        p_nom.setText(t_pro.getModel().getValueAt(col, 1).toString());
        p_can.setText(t_pro.getModel().getValueAt(col, 2).toString());
        pc_tipos.setSelectedItem(t_pro.getModel().getValueAt(col, 3).toString());
        p_des.setText(t_pro.getModel().getValueAt(col, 4).toString());

    }//GEN-LAST:event_t_proMouseClicked

    private void bus_pKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_bus_pKeyPressed

        String a = bus_p.getText();
        String sql = "select productos.id,nombre,cantidad,tipos.descripcion ,productos.descripcion  "
                + "from productos,tipos where productos.id_tipo=tipos.id "
                + "and (productos.id like '%" + a + "%' or nombre like '%" + a + "%' or cantidad like '%" + a + "%'  "
                + "or tipos.descripcion  like '%" + a + "%' or productos.descripcion like '%" + a + "%')";
        model = new DefaultTableModel(null, e_pr);
        try {
            datosbase = con.consultar(sql);
            while (datosbase.next()) {
                dte[0] = "" + datosbase.getString("id");
                dte[1] = "" + datosbase.getString("nombre");
                dte[2] = "" + datosbase.getString("cantidad");
                dte[3] = "" + datosbase.getString("tipos.descripcion");
                dte[4] = "" + datosbase.getString("productos.descripcion");
                model.addRow(dte);
            }
            t_pro.setModel(model);
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, ex);
        }
    }//GEN-LAST:event_bus_pKeyPressed

    private void bus_pActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bus_pActionPerformed

    }//GEN-LAST:event_bus_pActionPerformed

    private void t_empleadosMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_t_empleadosMouseClicked
        int col = t_empleados.getSelectedRow();
        id_e.setText(t_empleados.getModel().getValueAt(col, 0).toString());
        e_nom.setText(t_empleados.getModel().getValueAt(col, 1).toString());
        e_ap.setText(t_empleados.getModel().getValueAt(col, 2).toString());
        e_am.setText(t_empleados.getModel().getValueAt(col, 3).toString());
        e_edad.setSelectedItem(t_empleados.getModel().getValueAt(col, 4).toString());
        e_puesto.setSelectedItem(t_empleados.getModel().getValueAt(col, 5).toString());

    }//GEN-LAST:event_t_empleadosMouseClicked

    private void jButton10ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton10ActionPerformed
        e_nom.setText("");
        e_ap.setText("");
        e_am.setText("");
    }//GEN-LAST:event_jButton10ActionPerformed

    private void jButton11ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton11ActionPerformed
        String nom_e, ap_e, am_e, puesto_e, edad_e,e_id;
        
        e_id = id_e.getText();
        nom_e = e_nom.getText();
        ap_e = e_ap.getText();
        am_e = e_am.getText();
        edad_e = e_edad.getSelectedItem().toString();
        puesto_e = e_edad.getSelectedItem().toString();
        if (nom_e.equals("")) {
            JOptionPane.showMessageDialog(null, "es nesesaio insertar nombre");
        } else {
            if (ap_e.equals("")) {
                JOptionPane.showMessageDialog(null, "es nesesaio insertar cantidad");
            } else {
                if (am_e.equals("")) {
                    JOptionPane.showMessageDialog(null, "es nesesaio insertar descripcion");
                } else {
                    try {
                        int a = Integer.parseInt(edad_e);
                        int b = Integer.parseInt(e_id);
                        String r = con.procedure6(b,nom_e,ap_e,am_e,a,puesto_e);
                        if (r == null) {
                            JOptionPane.showMessageDialog(null, "Actualizado correctamente");
                            cargar_emp();
                            e_id="";nom_e= "";ap_e= "";am_e= "";edad_e= "";puesto_e = "";
                        } else {
                            JOptionPane.showMessageDialog(null, "" + r);
                        }
                    } catch (Exception ex) {
                        System.out.println(ex);
                        JOptionPane.showMessageDialog(null, "ha ocurrido un error al ingresar");
                        e_id="";
                        nom_e = "";
                        ap_e = "";
                        am_e = "";
                        edad_e = "";
                        puesto_e = "";
                    }

                }
            }
        }
    }//GEN-LAST:event_jButton11ActionPerformed

    private void jButton12ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton12ActionPerformed
        String id_em = id_e.getText();
        if (id_em == "") {
            JOptionPane.showMessageDialog(null, "No ha selecionado una direccion dentro del registro");
        } else {
            String sql = "delete from empleados where id=" + id_em + "";
            try {
                con.operacion(sql);
                cargar_emp();
                JOptionPane.showMessageDialog(null, "eliminado correctamente");
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(null, "error");
            }
        }

    }//GEN-LAST:event_jButton12ActionPerformed

    private void jButton13ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton13ActionPerformed
        String nom_e, ap_e, am_e, puesto_e, edad_e;
        nom_e = e_nom.getText();
        ap_e = e_ap.getText();
        am_e = e_am.getText();
        edad_e = e_edad.getSelectedItem().toString();
        puesto_e = e_edad.getSelectedItem().toString();
        if (nom_e.equals("")) {
            JOptionPane.showMessageDialog(null, "es nesesaio insertar nombre");
        } else {
            if (ap_e.equals("")) {
                JOptionPane.showMessageDialog(null, "es nesesaio insertar apellido paterno");
            } else {
                if (am_e.equals("")) {
                    JOptionPane.showMessageDialog(null, "es nesesaio insertar apellido materno");
                } else {
                    try {
                        int a = Integer.parseInt(edad_e);
                        String r = con.procedure5(nom_e,ap_e,am_e,a,puesto_e);
                        if (r == null) {
                            JOptionPane.showMessageDialog(null, "Ingresado correctamente");
                            cargar_emp();
                            nom_e= "";ap_e= "";am_e= "";edad_e= "";puesto_e = "";
                        } else {
                            JOptionPane.showMessageDialog(null, "" + r);
                        }
                    } catch (Exception ex) {
                        System.out.println(ex);
                        JOptionPane.showMessageDialog(null, "ha ocurrido un error al ingresar");
                        nom_e = "";
                        ap_e = "";
                        am_e = "";
                        edad_e = "";
                        puesto_e = "";
                    }

                }
            }
        }
    }//GEN-LAST:event_jButton13ActionPerformed

    private void bus_empActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bus_empActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_bus_empActionPerformed

    private void bus_empKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_bus_empKeyPressed
        String a = bus_emp.getText();
        String sql = "select personal.id,nombre ,ap, am,edad,puesto "
                + "from personal ,puestos where personal.id_puesto=puestos.id and "
                + "(personal.id like '%" + a + "%' or nombre like '%" + a + "%' or ap like '%" + a + "%' "
                + "or am like '%" + a + "%' or edad like '%" + a + "%' or puesto like '%" + a + "%')";
        model = new DefaultTableModel(null, e_emp);
        try {
            datosbase = con.consultar(sql);
            while (datosbase.next()) {
                dtem[0] = "" + datosbase.getString("id");
                dtem[1] = "" + datosbase.getString("nombre");
                dtem[2] = "" + datosbase.getString("ap");
                dtem[3] = "" + datosbase.getString("am");
                dtem[4] = "" + datosbase.getString("edad");
                dtem[5] = "" + datosbase.getString("puesto");
                model.addRow(dtem);
            }
            t_empleados.setModel(model);
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, ex);
        }
    }//GEN-LAST:event_bus_empKeyPressed

    private void jButton6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton6ActionPerformed
        p_1.setVisible(true);
        p_2.setVisible(false);
        p_3.setVisible(false);
        p_4.setVisible(false);
        p_5.setVisible(false);
    }//GEN-LAST:event_jButton6ActionPerformed

    private void jButton9ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton9ActionPerformed
        p_1.setVisible(false);
        p_2.setVisible(false);
        p_3.setVisible(false);
        p_4.setVisible(true);
        p_5.setVisible(false);
    }//GEN-LAST:event_jButton9ActionPerformed

    private void jButton7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton7ActionPerformed
        p_1.setVisible(false);
        p_2.setVisible(false);
        p_3.setVisible(false);
        p_4.setVisible(false);
        p_5.setVisible(true);
    }//GEN-LAST:event_jButton7ActionPerformed

    private void t_ventasMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_t_ventasMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_t_ventasMouseClicked

    private void jButton14ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton14ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton14ActionPerformed

    private void jButton15ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton15ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton15ActionPerformed

    private void jButton16ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton16ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton16ActionPerformed

    private void jButton17ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton17ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton17ActionPerformed

    private void bus_ventasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bus_ventasActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_bus_ventasActionPerformed

    private void bus_ventasKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_bus_ventasKeyPressed
        String a = bus_ventas.getText();
        String sql = "select ventas.id,productos.nombre,ventas.cantidad,direciones.pueblo,  "
                + "direciones.calle,personal.nombre,personal.am,personal.ap    "
                + "from ventas,personal,direciones,productos  where ventas.id_persona=personal.id  "
                + "and ventas.direccion=direciones.id and ventas.id_producto=productos.id and ( ventas.id like '%" + a + "%' "
                + "or productos.nombre like '%" + a + "%' or ventas.cantidad like '%" + a + "%' or direciones.pueblo like '%" + a + "%' "
                + "or  direciones.calle like '%" + a + "%' or  personal.nombre like '%" + a + "%' or personal.am like '%" + a + "%' "
                + "or personal.ap like '%" + a + "%'  )";
        model = new DefaultTableModel(null, e_vent);
        try {
            datosbase = con.consultar(sql);
            while (datosbase.next()) {
                dtven[0] = "" + datosbase.getString("ventas.id");
                dtven[1] = "" + datosbase.getString("productos.nombre");
                dtven[2] = "" + datosbase.getString("ventas.cantidad");
                dtven[3] = "" + datosbase.getString("direciones.pueblo");
                dtven[4] = "" + datosbase.getString("direciones.calle");
                dtven[5] = "" + datosbase.getString("personal.nombre");
                dtven[6] = "" + datosbase.getString("personal.am");
                dtven[7] = "" + datosbase.getString("personal.ap");
                model.addRow(dtven);
            }
            t_ventas.setModel(model);
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, ex);
        }
    }//GEN-LAST:event_bus_ventasKeyPressed

    private void t_puestosMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_t_puestosMouseClicked
        int col = t_puestos.getSelectedRow();
        id_pstt.setText(t_puestos.getModel().getValueAt(col, 0).toString());
        p_descripcion.setText(t_puestos.getModel().getValueAt(col, 1).toString());

    }//GEN-LAST:event_t_puestosMouseClicked

    private void jButton18ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton18ActionPerformed
        id_pstt.setText("");
        p_descripcion.setText("");


    }//GEN-LAST:event_jButton18ActionPerformed

    private void jButton19ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton19ActionPerformed
        String id_pst, p_descc;
        p_descc = p_descripcion.getText();
        id_pst = id_pstt.getText();
        if (id_pst == "") {
            JOptionPane.showMessageDialog(null, "No se ha selecionada un puesto del registro");
        } else {
            if (p_descc == "") {
                JOptionPane.showMessageDialog(null, "es nesesaio insertar descipcion");
            } else {
                try {
                    int a = Integer.parseInt(id_pst);
                    String r = con.procedure4(a, p_descc);
                    if (r == null) {
                        JOptionPane.showMessageDialog(null, "Actualizado correctamente");
                        cargar_put();
                        combos();
                        p_descc = "";
                    } else {
                        JOptionPane.showMessageDialog(null, "" + r);
                    }
                } catch (Exception ex) {
                    System.out.println("" + ex);
                    JOptionPane.showMessageDialog(null, "ha ocurrido un error al actualizar");
                    p_descc = "";
                }
            }
        }
    }//GEN-LAST:event_jButton19ActionPerformed

    private void jButton20ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton20ActionPerformed
        String id_pst = id_pstt.getText();
        if (id_pst == "") {
            JOptionPane.showMessageDialog(null, "No ha selecionado una direccion dentro del registro");
        } else {
            String sql = "delete from puestos where id=" + id_pst + "";
            try {
                con.operacion(sql);
                cargar_put();
                combos();
                JOptionPane.showMessageDialog(null, "eliminado correctamente");
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(null, "error");
            }
        }
    }//GEN-LAST:event_jButton20ActionPerformed

    private void jButton21ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton21ActionPerformed
        String p_descc;
        p_descc = p_descripcion.getText();

        if (p_descc == "") {
            JOptionPane.showMessageDialog(null, "es nesesaio insertar descripcion");
        } else {
            try {

                String r = con.procedure3(p_descc);
                if (r == null) {
                    JOptionPane.showMessageDialog(null, "agreagdo correctamente");
                    cargar_put();
                    combos();
                    p_descc = "";
                } else {
                    JOptionPane.showMessageDialog(null, "" + r);
                }
            } catch (Exception ex) {
                System.out.println(ex);
                JOptionPane.showMessageDialog(null, "ha ocurrido un error al actualizar");
                p_descc = "";
            }
        }

    }//GEN-LAST:event_jButton21ActionPerformed

    private void bus_puestosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bus_puestosActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_bus_puestosActionPerformed

    private void bus_puestosKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_bus_puestosKeyPressed
        String a = bus_puestos.getText();
        String sql = " select * from puestos where id like '%" + a + "%' or puesto like'%" + a + "%'";
        model = new DefaultTableModel(null, e_dir);
        try {
            datosbase = con.consultar(sql);
            while (datosbase.next()) {
                dtpst[0] = "" + datosbase.getString("id");
                dtpst[1] = "" + datosbase.getString("puesto");
                model.addRow(dtpst);
            }
            t_puestos.setModel(model);
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, ex);
        }
    }//GEN-LAST:event_bus_puestosKeyPressed

    private void t_dirMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_t_dirMouseClicked
        int col = t_dir.getSelectedRow();
        id_dir.setText(t_dir.getModel().getValueAt(col, 0).toString());
        d_pueblo.setText(t_dir.getModel().getValueAt(col, 1).toString());
        d_calle.setText(t_dir.getModel().getValueAt(col, 2).toString());

    }//GEN-LAST:event_t_dirMouseClicked

    private void jButton22ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton22ActionPerformed
        id_dir.setText("");
        d_pueblo.setText("");
        d_calle.setText("");
    }//GEN-LAST:event_jButton22ActionPerformed

    private void jButton23ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton23ActionPerformed
        String id_di, d_pu, d_call;
        id_di = id_dir.getText();
        d_pu = d_pueblo.getText();
        d_call = d_calle.getText();
        if (id_di == "") {
            JOptionPane.showMessageDialog(null, "No ha selecionado una direcion dentro del registro");
        } else {
            if (d_pu.equals("")) {
                JOptionPane.showMessageDialog(null, "es nesesaio insertar nombre");
            } else {
                if (d_call.equals("")) {
                    JOptionPane.showMessageDialog(null, "es nesesaio insertar cantidad");
                } else {

                    try {
                        int a = Integer.parseInt(id_di);
                        String r = con.procedure2(a, d_pu, d_call);
                        if (r == null) {
                            JOptionPane.showMessageDialog(null, "agreagdo correctamente");
                            cargar_dir();
                            id_di = "";
                            d_pu = "";
                            d_call = "";
                        } else {
                            JOptionPane.showMessageDialog(null, "" + r);
                        }
                    } catch (Exception ex) {
                        System.out.println("aqui" + ex);
                        JOptionPane.showMessageDialog(null, "ha ocurrido un error al actualizar");
                        id_di = "";
                        d_pu = "";
                        d_call = "";
                    }
                }
            }
        }
    }//GEN-LAST:event_jButton23ActionPerformed

    private void jButton24ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton24ActionPerformed
        String id_di = id_dir.getText();
        if (id_di == "") {
            JOptionPane.showMessageDialog(null, "No ha selecionado una direccion dentro del registro");
        } else {
            String sql = "delete from direciones where id=" + id_di + "";
            try {
                con.operacion(sql);
                cargar_dir();
                JOptionPane.showMessageDialog(null, "eliminado correctamente");
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(null, "error");
            }
        }
    }//GEN-LAST:event_jButton24ActionPerformed

    private void jButton25ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton25ActionPerformed
        String pueblo, calle;

        pueblo = d_pueblo.getText();
        calle = d_calle.getText();

        if (pueblo.equals("")) {
            JOptionPane.showMessageDialog(null, "es nesesaio insertar pueblo");
        } else {
            if (calle.equals("")) {
                JOptionPane.showMessageDialog(null, "es nesesaio insertar calle");
            }

            try {
                String r = con.procedure1(pueblo, calle);
                if (r == null) {
                    JOptionPane.showMessageDialog(null, "agreagdo correctamente");
                    cargar_dir();
                    calle = "";
                    pueblo = "";
                } else {
                    JOptionPane.showMessageDialog(null, "" + r);
                }
            } catch (Exception ex) {
                System.out.println(ex);
                JOptionPane.showMessageDialog(null, "ha ocurrido un error al ingresar");
                calle = "";
                pueblo = "";
            }

        }

    }//GEN-LAST:event_jButton25ActionPerformed

    private void bus_direcionesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bus_direcionesActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_bus_direcionesActionPerformed

    private void bus_direcionesKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_bus_direcionesKeyPressed
        String a = bus_direciones.getText();
        String sql = "select * from direciones where id like '%" + a + "%' or pueblo like '%" + a + "%' or calle like '%" + a + "%';";
        model = new DefaultTableModel(null, e_p);
        try {
            datosbase = con.consultar(sql);
            while (datosbase.next()) {
                dtps[0] = "" + datosbase.getString("id");
                dtps[1] = "" + datosbase.getString("pueblo");
                dtps[2] = "" + datosbase.getString("calle");
                model.addRow(dtps);
            }
            t_dir.setModel(model);
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, ex);
        }
    }//GEN-LAST:event_bus_direcionesKeyPressed

    private void jButton8ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton8ActionPerformed
        p_1.setVisible(false);
        p_2.setVisible(false);
        p_3.setVisible(true);
        p_4.setVisible(false);
        p_5.setVisible(false);
    }//GEN-LAST:event_jButton8ActionPerformed

    private void jButton5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton5ActionPerformed
        p_1.setVisible(false);
        p_2.setVisible(true);
        p_3.setVisible(false);
        p_4.setVisible(false);
        p_5.setVisible(false);
    }//GEN-LAST:event_jButton5ActionPerformed
    public static void main(String args[]) {

        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(abp_index.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(abp_index.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(abp_index.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(abp_index.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new abp_index().setVisible(true);
            }
        });
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextField bus_direciones;
    private javax.swing.JTextField bus_emp;
    private javax.swing.JTextField bus_p;
    private javax.swing.JTextField bus_puestos;
    private javax.swing.JTextField bus_ventas;
    private javax.swing.JTextField d_calle;
    private javax.swing.JTextField d_pueblo;
    private javax.swing.JTextField e_am;
    private javax.swing.JTextField e_ap;
    private javax.swing.JComboBox<String> e_edad;
    private javax.swing.JTextField e_nom;
    private javax.swing.JComboBox<String> e_puesto;
    private javax.swing.JLabel id;
    private javax.swing.JLabel id2;
    private javax.swing.JLabel id_dir;
    private javax.swing.JLabel id_e;
    private javax.swing.JLabel id_pstt;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton10;
    private javax.swing.JButton jButton11;
    private javax.swing.JButton jButton12;
    private javax.swing.JButton jButton13;
    private javax.swing.JButton jButton14;
    private javax.swing.JButton jButton15;
    private javax.swing.JButton jButton16;
    private javax.swing.JButton jButton17;
    private javax.swing.JButton jButton18;
    private javax.swing.JButton jButton19;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton20;
    private javax.swing.JButton jButton21;
    private javax.swing.JButton jButton22;
    private javax.swing.JButton jButton23;
    private javax.swing.JButton jButton24;
    private javax.swing.JButton jButton25;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JButton jButton5;
    private javax.swing.JButton jButton6;
    private javax.swing.JButton jButton7;
    private javax.swing.JButton jButton8;
    private javax.swing.JButton jButton9;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel22;
    private javax.swing.JLabel jLabel25;
    private javax.swing.JLabel jLabel26;
    private javax.swing.JLabel jLabel27;
    private javax.swing.JLabel jLabel28;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel31;
    private javax.swing.JLabel jLabel32;
    private javax.swing.JLabel jLabel33;
    private javax.swing.JLabel jLabel34;
    private javax.swing.JLabel jLabel35;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JScrollPane jScrollPane5;
    private javax.swing.JLabel n_emp;
    private javax.swing.JLabel n_emp2;
    private javax.swing.JLabel n_emp3;
    private javax.swing.JLabel n_emp4;
    private javax.swing.JPanel p_1;
    private javax.swing.JPanel p_2;
    private javax.swing.JPanel p_3;
    private javax.swing.JPanel p_4;
    private javax.swing.JPanel p_5;
    private javax.swing.JTextField p_can;
    private javax.swing.JTextField p_can2;
    private javax.swing.JTextField p_des;
    private javax.swing.JTextField p_des2;
    private javax.swing.JTextField p_descripcion;
    private javax.swing.JTextField p_nom;
    private javax.swing.JTextField p_nom2;
    private javax.swing.JComboBox<String> pc_tipos;
    private javax.swing.JComboBox<String> pc_tipos2;
    private javax.swing.JTable t_dir;
    private javax.swing.JTable t_empleados;
    private javax.swing.JTable t_pro;
    private javax.swing.JTable t_puestos;
    private javax.swing.JTable t_ventas;
    // End of variables declaration//GEN-END:variables
}
