/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ClasesAuxiliares;

import Consola.Usuario;
import Consola.UsuarioAdmin;
import FileSystem.Archivo;

/**
 *
 * @author Santiago
 */
public class Utils {

    public static String convertirMascara(int mascara) {
        String masc = mascara + "";
        String mascaraString = "";
        if (masc.length() == 3 && verificarMascara(masc)) {
            for (int i = 0; i < 3; i++) {
                mascaraString += convertir(masc.charAt(i) + "");
            }
        }
        return mascaraString;
    }

    private static boolean verificarMascara(String masc) {
        try {
            for (int i = 0; i < 3; i++) {
                int nro = Integer.parseInt(masc.charAt(i) + "");
                if (nro < 0 || nro > 7) {
                    return false;
                }
            }
        } catch (NumberFormatException ex) {
            return false;
        }
        return true;
    }

    private static String convertir(String mascVal) {
        switch (mascVal) {
            case "0":
                return "---";
            case "1":
                return "--x";
            case "2":
                return "-w-";
            case "3":
                return "-wx";
            case "4":
                return "r--";
            case "5":
                return "r-x";
            case "6":
                return "rw-";
            case "7":
                return "rwx";
            default:
                return "---";
        }
    }

    public static boolean canRead(Usuario user, Archivo arch) {
        try {
            if (user.equals(arch.getPropietario())) {
                String mascara = arch.getMascara() + "";
                String permisosPropietario = mascara.charAt(0) + "";
                int permiso = Integer.parseInt(permisosPropietario);
                return permiso >= 4 && permiso <= 7;
            } else if (user.getClass().equals(UsuarioAdmin.class)) {
                return true;
            } else {
                String mascara = arch.getMascara() + "";
                String permisosPropietario = mascara.charAt(2) + "";
                int permiso = Integer.parseInt(permisosPropietario);
                return permiso >= 4 && permiso <= 7;
            }
        } catch (NumberFormatException ex) {
            System.out.println("ERROR numberformatEx at ClasesAuxiliares.Utils.canRead()");
            return false;
        }
    }

    public static boolean canWrite(Usuario user, Archivo arch) {
        try {
            if (user.equals(arch.getPropietario())) {
                String mascara = arch.getMascara() + "";
                String permisosPropietario = mascara.charAt(0) + "";
                int permiso = Integer.parseInt(permisosPropietario);
                return permiso == 2 || permiso == 3 || permiso == 6 || permiso == 7;
            } else if (user.getClass().equals(UsuarioAdmin.class)) {
                return true;
            } else {
                String mascara = arch.getMascara() + "";
                String permisosPropietario = mascara.charAt(2) + "";
                int permiso = Integer.parseInt(permisosPropietario);
                return permiso == 2 || permiso == 3 || permiso == 6 || permiso == 7;
            }
        } catch (NumberFormatException ex) {
            System.out.println("ERROR numberformatEx at ClasesAuxiliares.Utils.canWrite()");
            return false;
        }
    }

    public static boolean canExecute(Usuario user, Archivo arch) {
        try {
            if (user.equals(arch.getPropietario())) {
                String mascara = arch.getMascara() + "";
                String permisosPropietario = mascara.charAt(0) + "";
                int permiso = Integer.parseInt(permisosPropietario);
                return permiso == 1 || permiso == 3 || permiso == 5 || permiso == 7;
            } else if (user.getClass().equals(UsuarioAdmin.class)) {
                return true;
            } else {
                String mascara = arch.getMascara() + "";
                String permisosPropietario = mascara.charAt(2) + "";
                int permiso = Integer.parseInt(permisosPropietario);
                return permiso == 1 || permiso == 3 || permiso == 5 || permiso == 7;
            }
        } catch (NumberFormatException ex) {
            System.out.println("ERROR numberformatEx at ClasesAuxiliares.Utils.canExecute()");
            return false;
        }
    }
}
