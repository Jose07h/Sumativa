package abp;

import java.sql.*;

public class ConexionBD {

    static String bd = "made";//nombre de la base 
    //static String login ="gollermo";
    static String login = "root";
    static String password;
    static String url = "jdbc:mysql://localhost/" + bd;
    static boolean conectado;
    static Statement instruccion = null;//operacion insert,delete,update
    static ResultSet resultados = null;//almacena datos obtenidos
    static int filas = 0;
    static String controlador = "com.mysql.jdbc.Driver";
    static Connection conn;
    static PreparedStatement sqll = null;
    CallableStatement rs = null;

    public ConexionBD() {
        conn = null;
        try {
            Class.forName(controlador).newInstance();
            conn = DriverManager.getConnection(url, login, "");
            instruccion = conn.createStatement();

            if (conn != null) {
                System.out.println("Conexion ala base de batos"
                        + url + " ... ok");
                conectado = true;
            }
        } catch (SQLException ex) {
            System.out.println("Hubo un problema al intentar" + "conectarse con la base de datos " + ex.getMessage());
            conectado = false;
        } catch (ClassNotFoundException ex) {
            System.out.println(ex);
            conectado = false;
        } catch (Exception m) {
            conectado = false;
        }
    }

    public void operacion(String sentencia) throws Exception//insertacion de datos
    {
        instruccion.executeUpdate(sentencia);
    }

    public void proced(String sentencia) throws Exception//insertacion de datos
    {
        sqll = conn.prepareCall(sentencia);
        sqll.executeUpdate();
    }

    public String procedure1(String a, String b) {
        String resultado = null;
        try {
            CallableStatement proc = conn.prepareCall(" CALL inserta_direciones(?,?,?) ");
            proc.setString("pueblo_e", a);
            proc.setString("calle_e", b);
            proc.registerOutParameter("res", Types.VARCHAR);
            proc.execute();
            resultado = proc.getString("res");
        } catch (Exception e) {
            System.out.println(e);
        }
        return resultado;
    }

    public String procedure2(int a, String b,String c) {
        String resultado = null;
        try {
            CallableStatement proc = conn.prepareCall(" CALL actualiza_direciones(?,?,?,?) ");
            proc.setInt("id_e", a);
            proc.setString("pueblo_e", b);
            proc.setString("calle_e", c);
            proc.registerOutParameter("res", Types.VARCHAR);
            proc.execute();
            resultado = proc.getString("res");
        } catch (Exception e) {
            System.out.println(e);
        }
        return resultado;
    }

    public String procedure3(String a) {
        String resultado = null;
        try {
            CallableStatement proc = conn.prepareCall(" CALL inserta_puestos(?,?) ");
            proc.setString("descr_e", a);
            proc.registerOutParameter("res", Types.VARCHAR);
            proc.execute();
            resultado = proc.getString("res");
        } catch (Exception e) {
            System.out.println(e);
        }
        return resultado;
    }
    public String procedure4(int a,String b) {
        String resultado = null;
        try {
            CallableStatement proc = conn.prepareCall(" CALL actualiza_puestos(?,?,?) ");
            proc.setInt("id_e", a);
            proc.setString("descr_e", b);
            proc.registerOutParameter("res", Types.VARCHAR);
            proc.execute();
            resultado = proc.getString("res");
        } catch (Exception e) {
            System.out.println(""+e);
        }
        return resultado;
    }
public String procedure5(String a,String b,String c,int d,String e) {
        String resultado = null;
        try {
            CallableStatement proc = conn.prepareCall(" CALL inserta_empleados(?,?,?,?,?,?) ");
            proc.setString("nombre_e", a);
            proc.setString("ap_e", b);
            proc.setString("am_e", c);
            proc.setInt("edad_e", d);
            proc.setString("puesto_e", e);
            proc.registerOutParameter("res", Types.VARCHAR);
            proc.execute();
            resultado = proc.getString("res");
        } catch (Exception f) {
            System.out.println(""+f);
        }
        return resultado;
    }
public String procedure6(int n,String a,String b,String c,int d,String e) {
        String resultado = null;
        try {
            CallableStatement proc = conn.prepareCall(" CALL actualiza_empleados(?,?,?,?,?,?,?)");
            proc.setInt("id_e", n);
            proc.setString("nombre_e", a);
            proc.setString("ap_e", b);
            proc.setString("am_e", c);
            proc.setInt("edad_e", d);
            proc.setString("puesto_e", e);
            proc.registerOutParameter("res", Types.VARCHAR);
            proc.execute();
            resultado = proc.getString("res");
        } catch (Exception f) {
            System.out.println(""+f);
        }
        return resultado;
    }
    public static ResultSet consultar(String sentenciaSQL)//select*from
    {
        ResultSet res = null;
        filas = 0;
        try {
            res = instruccion.executeQuery(sentenciaSQL);
            while (res.next()) {
                filas++;
            }
            res = instruccion.executeQuery(sentenciaSQL);
            return res;
        } catch (Exception m) {
            System.out.println("Error en la consulta" + m.getMessage());
        }
        return res;
    }

    public static int obtenerMaximo() {
        return filas;
    }

    public static void main(String args[]) {
        ConexionBD bd = new ConexionBD();
    }
}
