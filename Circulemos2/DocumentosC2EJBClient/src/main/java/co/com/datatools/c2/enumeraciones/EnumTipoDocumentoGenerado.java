package co.com.datatools.c2.enumeraciones;

import co.com.datatools.c2.negocio.interfaces.IPlantillaGenerable;
import co.com.datatools.c2.util.SearchableEnumeration;

/**
 * Enumeracion para generacion de documentos mediante la caja negra
 * 
 * @author julio.pinzon
 * 
 */
public enum EnumTipoDocumentoGenerado implements SearchableEnumeration<Integer>, IPlantillaGenerable {

    NOTIFICACION_PERSONAL(1, "/c2/comparendo/notificacion/personal", "NOT_PER_BOG",
            "Notificación personal del comparendo", false), //
    NOTIFICACION_POR_CONDUCTA_CONCLUYENTE(2, "/c2/comparendo/notificacion/conducta_concluyente", "NTIF_CONDU_CONY",
            "Notificación por conducta concluyente del comparendo", false), //
    NOTIF_CORREO_CERTIF(3, "/c2/%s/Notificacion_comparendos", "NOT_PRO_CONT_TR_INFR",
            "Notificación por correo certificado", false), //
    NOTIF_AVISO_COMPA(4, "/c2/%s/Notificacion_comparendos/%s", "NOT_AVISO_TRAN", "Notificacion por aviso", false), //

    RESOLUCION_SANCION_A01(5, "/c2/comparendo/resoluciones/sancion/a01", "RE_PLAN_SOACHA", "Resolución de Sanción A01",
            true), //
    RESOLUCION_SANCION_A02(6, "/c2/comparendo/resoluciones/sancion/a02", "RE_PLAN_SOACHA", "Resolución de Sanción A02",
            true), //
    RESOLUCION_SANCION_A03(7, "/c2/comparendo/resoluciones/sancion/a03", "RE_PLAN_SOACHA", "Resolución de Sanción A03",
            true), //
    RESOLUCION_SANCION_A04(8, "/c2/comparendo/resoluciones/sancion/a04", "RE_PLAN_SOACHA", "Resolución de Sanción A04",
            true), //
    RESOLUCION_SANCION_A05(9, "/c2/comparendo/resoluciones/sancion/a05", "RE_PLAN_SOACHA", "Resolución de Sanción A05",
            true), //
    RESOLUCION_SANCION_A06(10, "/c2/comparendo/resoluciones/sancion/a06", "RE_PLAN_SOACHA", "Resolución de Sanción A06",
            true), //
    RESOLUCION_SANCION_A07(11, "/c2/comparendo/resoluciones/sancion/a07", "RE_PLAN_SOACHA", "Resolución de Sanción A07",
            true), //
    RESOLUCION_SANCION_A08(12, "/c2/comparendo/resoluciones/sancion/a08", "RE_PLAN_SOACHA", "Resolución de Sanción A08",
            true), //
    RESOLUCION_SANCION_A09(13, "/c2/comparendo/resoluciones/sancion/a09", "RE_PLAN_SOACHA", "Resolución de Sanción A09",
            true), //
    RESOLUCION_SANCION_A10(14, "/c2/comparendo/resoluciones/sancion/a10", "RE_PLAN_SOACHA", "Resolución de Sanción A10",
            true), //
    RESOLUCION_SANCION_A11(15, "/c2/comparendo/resoluciones/sancion/a11", "RE_PLAN_SOACHA", "Resolución de Sanción A11",
            true), //
    RESOLUCION_SANCION_A12(16, "/c2/comparendo/resoluciones/sancion/a12", "RE_PLAN_SOACHA", "Resolución de Sanción A12",
            true), //
    RESOLUCION_SANCION_B01(17, "/c2/comparendo/resoluciones/sancion/b01", "RE_PLAN_SOACHA", "Resolución de Sanción B01",
            true), //
    RESOLUCION_SANCION_B02(18, "/c2/comparendo/resoluciones/sancion/b02", "RE_PLAN_SOACHA", "Resolución de Sanción B02",
            true), //
    RESOLUCION_SANCION_B03(19, "/c2/comparendo/resoluciones/sancion/b03", "RE_PLAN_SOACHA", "Resolución de Sanción B03",
            true), //
    RESOLUCION_SANCION_B04(20, "/c2/comparendo/resoluciones/sancion/b04", "RE_PLAN_SOACHA", "Resolución de Sanción B04",
            true), //
    RESOLUCION_SANCION_B05(21, "/c2/comparendo/resoluciones/sancion/b05", "RE_PLAN_SOACHA", "Resolución de Sanción B05",
            true), //
    RESOLUCION_SANCION_B06(22, "/c2/comparendo/resoluciones/sancion/b06", "RE_PLAN_SOACHA", "Resolución de Sanción B06",
            true), //
    RESOLUCION_SANCION_B07(23, "/c2/comparendo/resoluciones/sancion/b07", "RE_PLAN_SOACHA", "Resolución de Sanción B07",
            true), //
    RESOLUCION_SANCION_B08(24, "/c2/comparendo/resoluciones/sancion/b08", "RE_PLAN_SOACHA", "Resolución de Sanción B08",
            true), //
    RESOLUCION_SANCION_B09(25, "/c2/comparendo/resoluciones/sancion/b09", "RE_PLAN_SOACHA", "Resolución de Sanción B09",
            true), //
    RESOLUCION_SANCION_B10(26, "/c2/comparendo/resoluciones/sancion/b10", "RE_PLAN_SOACHA", "Resolución de Sanción B10",
            true), //
    RESOLUCION_SANCION_B11(27, "/c2/comparendo/resoluciones/sancion/b11", "RE_PLAN_SOACHA", "Resolución de Sanción B11",
            true), //
    RESOLUCION_SANCION_B12(28, "/c2/comparendo/resoluciones/sancion/b12", "RE_PLAN_SOACHA", "Resolución de Sanción B12",
            true), //
    RESOLUCION_SANCION_B13(29, "/c2/comparendo/resoluciones/sancion/b13", "RE_PLAN_SOACHA", "Resolución de Sanción B13",
            true), //
    RESOLUCION_SANCION_B14(30, "/c2/comparendo/resoluciones/sancion/b14", "RE_PLAN_SOACHA", "Resolución de Sanción B14",
            true), //
    RESOLUCION_SANCION_B15(31, "/c2/comparendo/resoluciones/sancion/b15", "RE_PLAN_SOACHA", "Resolución de Sanción B15",
            true), //
    RESOLUCION_SANCION_B16(32, "/c2/comparendo/resoluciones/sancion/b16", "RE_PLAN_SOACHA", "Resolución de Sanción B16",
            true), //
    RESOLUCION_SANCION_B17(33, "/c2/comparendo/resoluciones/sancion/b17", "RE_PLAN_SOACHA", "Resolución de Sanción B17",
            true), //
    RESOLUCION_SANCION_B18(34, "/c2/comparendo/resoluciones/sancion/b18", "RE_PLAN_SOACHA", "Resolución de Sanción B18",
            true), //
    RESOLUCION_SANCION_B19(35, "/c2/comparendo/resoluciones/sancion/b19", "RE_PLAN_SOACHA", "Resolución de Sanción B19",
            true), //
    RESOLUCION_SANCION_B20(36, "/c2/comparendo/resoluciones/sancion/b20", "RE_PLAN_SOACHA", "Resolución de Sanción B20",
            true), //
    RESOLUCION_SANCION_B21(37, "/c2/comparendo/resoluciones/sancion/b21", "RE_PLAN_SOACHA", "Resolución de Sanción B21",
            true), //
    RESOLUCION_SANCION_B22(38, "/c2/comparendo/resoluciones/sancion/b22", "RE_PLAN_SOACHA", "Resolución de Sanción B22",
            true), //
    RESOLUCION_SANCION_B23(39, "/c2/comparendo/resoluciones/sancion/b23", "RE_PLAN_SOACHA", "Resolución de Sanción B23",
            true), //
    RESOLUCION_SANCION_C01(40, "/c2/comparendo/resoluciones/sancion/c01", "RE_PLAN_SOACHA", "Resolución de Sanción C01",
            true), //
    RESOLUCION_SANCION_C02(41, "/c2/comparendo/resoluciones/sancion/c02", "RE_PLAN_SOACHA", "Resolución de Sanción C02",
            true), //
    RESOLUCION_SANCION_C03(42, "/c2/comparendo/resoluciones/sancion/c03", "RE_PLAN_SOACHA", "Resolución de Sanción C03",
            true), //
    RESOLUCION_SANCION_C04(43, "/c2/comparendo/resoluciones/sancion/c04", "RE_PLAN_SOACHA", "Resolución de Sanción C04",
            true), //
    RESOLUCION_SANCION_C05(44, "/c2/comparendo/resoluciones/sancion/c05", "RE_PLAN_SOACHA", "Resolución de Sanción C05",
            true), //
    RESOLUCION_SANCION_C06(45, "/c2/comparendo/resoluciones/sancion/c06", "RE_PLAN_SOACHA", "Resolución de Sanción C06",
            true), //
    RESOLUCION_SANCION_C07(46, "/c2/comparendo/resoluciones/sancion/c07", "RE_PLAN_SOACHA", "Resolución de Sanción C07",
            true), //
    RESOLUCION_SANCION_C08(47, "/c2/comparendo/resoluciones/sancion/c08", "RE_PLAN_SOACHA", "Resolución de Sanción C08",
            true), //
    RESOLUCION_SANCION_C09(48, "/c2/comparendo/resoluciones/sancion/c09", "RE_PLAN_SOACHA", "Resolución de Sanción C09",
            true), //
    RESOLUCION_SANCION_C10(49, "/c2/comparendo/resoluciones/sancion/c10", "RE_PLAN_SOACHA", "Resolución de Sanción C10",
            true), //
    RESOLUCION_SANCION_C11(50, "/c2/comparendo/resoluciones/sancion/c11", "RE_PLAN_SOACHA", "Resolución de Sanción C11",
            true), //
    RESOLUCION_SANCION_C12(51, "/c2/comparendo/resoluciones/sancion/c12", "RE_PLAN_SOACHA", "Resolución de Sanción C12",
            true), //
    RESOLUCION_SANCION_C13(52, "/c2/comparendo/resoluciones/sancion/c13", "RE_PLAN_SOACHA", "Resolución de Sanción C13",
            true), //
    RESOLUCION_SANCION_C14(53, "/c2/comparendo/resoluciones/sancion/c14", "RE_PLAN_SOACHA", "Resolución de Sanción C14",
            true), //
    RESOLUCION_SANCION_C15(54, "/c2/comparendo/resoluciones/sancion/c15", "RE_PLAN_SOACHA", "Resolución de Sanción C15",
            true), //
    RESOLUCION_SANCION_C16(55, "/c2/comparendo/resoluciones/sancion/c16", "RE_PLAN_SOACHA", "Resolución de Sanción C16",
            true), //
    RESOLUCION_SANCION_C17(56, "/c2/comparendo/resoluciones/sancion/c17", "RE_PLAN_SOACHA", "Resolución de Sanción C17",
            true), //
    RESOLUCION_SANCION_C18(57, "/c2/comparendo/resoluciones/sancion/c18", "RE_PLAN_SOACHA", "Resolución de Sanción C18",
            true), //
    RESOLUCION_SANCION_C19(58, "/c2/comparendo/resoluciones/sancion/c19", "RE_PLAN_SOACHA", "Resolución de Sanción C19",
            true), //
    RESOLUCION_SANCION_C20(59, "/c2/comparendo/resoluciones/sancion/c20", "RE_PLAN_SOACHA", "Resolución de Sanción C20",
            true), //
    RESOLUCION_SANCION_C21(60, "/c2/comparendo/resoluciones/sancion/c21", "RE_PLAN_SOACHA", "Resolución de Sanción C21",
            true), //
    RESOLUCION_SANCION_C22(61, "/c2/comparendo/resoluciones/sancion/c22", "RE_PLAN_SOACHA", "Resolución de Sanción C22",
            true), //
    RESOLUCION_SANCION_C23(62, "/c2/comparendo/resoluciones/sancion/c23", "RE_PLAN_SOACHA", "Resolución de Sanción C23",
            true), //
    RESOLUCION_SANCION_C24(63, "/c2/comparendo/resoluciones/sancion/c24", "RE_PLAN_SOACHA", "Resolución de Sanción C24",
            true), //
    RESOLUCION_SANCION_C25(64, "/c2/comparendo/resoluciones/sancion/c25", "RE_PLAN_SOACHA", "Resolución de Sanción C25",
            true), //
    RESOLUCION_SANCION_C26(65, "/c2/comparendo/resoluciones/sancion/c26", "RE_PLAN_SOACHA", "Resolución de Sanción C26",
            true), //
    RESOLUCION_SANCION_C27(66, "/c2/comparendo/resoluciones/sancion/c27", "RE_PLAN_SOACHA", "Resolución de Sanción C27",
            true), //
    RESOLUCION_SANCION_C28(67, "/c2/comparendo/resoluciones/sancion/c28", "RE_PLAN_SOACHA", "Resolución de Sanción C28",
            true), //
    RESOLUCION_SANCION_C29(68, "/c2/comparendo/resoluciones/sancion/c29", "RE_PLAN_SOACHA", "Resolución de Sanción C29",
            true), //
    RESOLUCION_SANCION_C30(69, "/c2/comparendo/resoluciones/sancion/c30", "RE_PLAN_SOACHA", "Resolución de Sanción C30",
            true), //
    RESOLUCION_SANCION_C31(70, "/c2/comparendo/resoluciones/sancion/c31", "RE_PLAN_SOACHA", "Resolución de Sanción C31",
            true), //
    RESOLUCION_SANCION_C32(71, "/c2/comparendo/resoluciones/sancion/c32", "RE_PLAN_SOACHA", "Resolución de Sanción C32",
            true), //
    RESOLUCION_SANCION_C33(72, "/c2/comparendo/resoluciones/sancion/c33", "RE_PLAN_SOACHA", "Resolución de Sanción C33",
            true), //
    RESOLUCION_SANCION_C34(73, "/c2/comparendo/resoluciones/sancion/c34", "RE_PLAN_SOACHA", "Resolución de Sanción C34",
            true), //
    RESOLUCION_SANCION_C35(74, "/c2/comparendo/resoluciones/sancion/c35", "RE_PLAN_SOACHA", "Resolución de Sanción C35",
            true), //
    RESOLUCION_SANCION_C36(75, "/c2/comparendo/resoluciones/sancion/c36", "RE_PLAN_SOACHA", "Resolución de Sanción C36",
            true), //
    RESOLUCION_SANCION_C37(76, "/c2/comparendo/resoluciones/sancion/c37", "RE_PLAN_SOACHA", "Resolución de Sanción C37",
            true), //
    RESOLUCION_SANCION_C38(77, "/c2/comparendo/resoluciones/sancion/c38", "RE_PLAN_SOACHA", "Resolución de Sanción C38",
            true), //
    RESOLUCION_SANCION_C39(78, "/c2/comparendo/resoluciones/sancion/c39", "RE_PLAN_SOACHA", "Resolución de Sanción C39",
            true), //
    RESOLUCION_SANCION_C40(79, "/c2/comparendo/resoluciones/sancion/c40", "RE_PLAN_SOACHA", "Resolución de Sanción C40",
            true), //
    RESOLUCION_SANCION_D01(80, "/c2/comparendo/resoluciones/sancion/d01", "RE_PLAN_SOACHA", "Resolución de Sanción D01",
            true), //
    RESOLUCION_SANCION_D02(81, "/c2/comparendo/resoluciones/sancion/d02", "RE_PLAN_SOACHA", "Resolución de Sanción D02",
            true), //
    RESOLUCION_SANCION_D03(82, "/c2/comparendo/resoluciones/sancion/d03", "RE_PLAN_SOACHA", "Resolución de Sanción D03",
            true), //
    RESOLUCION_SANCION_D04(83, "/c2/comparendo/resoluciones/sancion/d04", "RE_PLAN_SOACHA", "Resolución de Sanción D04",
            true), //
    RESOLUCION_SANCION_D05(84, "/c2/comparendo/resoluciones/sancion/d05", "RE_PLAN_SOACHA", "Resolución de Sanción D05",
            true), //
    RESOLUCION_SANCION_D06(85, "/c2/comparendo/resoluciones/sancion/d06", "RE_PLAN_SOACHA", "Resolución de Sanción D06",
            true), //
    RESOLUCION_SANCION_D07(86, "/c2/comparendo/resoluciones/sancion/d07", "RE_PLAN_SOACHA", "Resolución de Sanción D07",
            true), //
    RESOLUCION_SANCION_D08(87, "/c2/comparendo/resoluciones/sancion/d08", "RE_PLAN_SOACHA", "Resolución de Sanción D08",
            true), //
    RESOLUCION_SANCION_D09(88, "/c2/comparendo/resoluciones/sancion/d09", "RE_PLAN_SOACHA", "Resolución de Sanción D09",
            true), //
    RESOLUCION_SANCION_D10(89, "/c2/comparendo/resoluciones/sancion/d10", "RE_PLAN_SOACHA", "Resolución de Sanción D10",
            true), //
    RESOLUCION_SANCION_D11(90, "/c2/comparendo/resoluciones/sancion/d11", "RE_PLAN_SOACHA", "Resolución de Sanción D11",
            true), //
    RESOLUCION_SANCION_D12(91, "/c2/comparendo/resoluciones/sancion/d12", "RE_PLAN_SOACHA", "Resolución de Sanción D12",
            true), //
    RESOLUCION_SANCION_D13(92, "/c2/comparendo/resoluciones/sancion/d13", "RE_PLAN_SOACHA", "Resolución de Sanción D13",
            true), //
    RESOLUCION_SANCION_D14(93, "/c2/comparendo/resoluciones/sancion/d14", "RE_PLAN_SOACHA", "Resolución de Sanción D14",
            true), //
    RESOLUCION_SANCION_D15(94, "/c2/comparendo/resoluciones/sancion/d15", "RE_PLAN_SOACHA", "Resolución de Sanción D15",
            true), //
    RESOLUCION_SANCION_D16(95, "/c2/comparendo/resoluciones/sancion/d16", "RE_PLAN_SOACHA", "Resolución de Sanción D16",
            true), //
    RESOLUCION_SANCION_D17(96, "/c2/comparendo/resoluciones/sancion/d17", "RE_PLAN_SOACHA", "Resolución de Sanción D17",
            true), //
    RESOLUCION_SANCION_E01(97, "/c2/comparendo/resoluciones/sancion/e01", "RE_PLAN_SOACHA", "Resolución de Sanción E01",
            true), //
    RESOLUCION_SANCION_E02(98, "/c2/comparendo/resoluciones/sancion/e02", "RE_PLAN_SOACHA", "Resolución de Sanción E02",
            true), //
    RESOLUCION_SANCION_E03(99, "/c2/comparendo/resoluciones/sancion/e03", "RE_PLAN_SOACHA", "Resolución de Sanción E03",
            true), //
    RESOLUCION_SANCION_E04(100, "/c2/comparendo/resoluciones/sancion/e04", "RE_PLAN_SOACHA",
            "Resolución de Sanción E04", true), //
    RESOLUCION_SANCION_F01(101, "/c2/comparendo/resoluciones/sancion/f01", "RE_PLAN_SOACHA",
            "Resolución de Sanción F01", true), //
    RESOLUCION_SANCION_F02(102, "/c2/comparendo/resoluciones/sancion/f02", "RE_PLAN_SOACHA",
            "Resolución de Sanción F02", true), //
    RESOLUCION_SANCION_F03(103, "/c2/comparendo/resoluciones/sancion/f03", "RE_PLAN_SOACHA",
            "Resolución de Sanción F03", true), //
    RESOLUCION_SANCION_F04(104, "/c2/comparendo/resoluciones/sancion/f04", "RE_PLAN_SOACHA",
            "Resolución de Sanción F04", true), //
    RESOLUCION_SANCION_F05(105, "/c2/comparendo/resoluciones/sancion/f05", "RE_PLAN_SOACHA",
            "Resolución de Sanción F05", true), //
    RESOLUCION_SANCION_F06(106, "/c2/comparendo/resoluciones/sancion/f06", "RE_PLAN_SOACHA",
            "Resolución de Sanción F06", true), //
    RESOLUCION_SANCION_F07(107, "/c2/comparendo/resoluciones/sancion/f07", "RE_PLAN_SOACHA",
            "Resolución de Sanción F07", true), //
    RESOLUCION_SANCION_F08(108, "/c2/comparendo/resoluciones/sancion/f08", "RE_PLAN_SOACHA",
            "Resolución de Sanción F08", true), //
    RESOLUCION_SANCION_F09(109, "/c2/comparendo/resoluciones/sancion/f09", "RE_PLAN_SOACHA",
            "Resolución de Sanción F09", true), //
    RESOLUCION_SANCION_F10(110, "/c2/comparendo/resoluciones/sancion/f10", "RE_PLAN_SOACHA",
            "Resolución de Sanción F10", true), //
    RESOLUCION_SANCION_F11(111, "/c2/comparendo/resoluciones/sancion/f11", "RE_PLAN_SOACHA",
            "Resolución de Sanción F11", true), //
    RESOLUCION_SANCION_F12(112, "/c2/comparendo/resoluciones/sancion/f12", "RE_PLAN_SOACHA",
            "Resolución de Sanción F12", true), //
    RESOLUCION_SANCION_G01(113, "/c2/comparendo/resoluciones/sancion/g01", "RE_PLAN_SOACHA",
            "Resolución de Sanción G01", true), //
    RESOLUCION_SANCION_G02(114, "/c2/comparendo/resoluciones/sancion/g02", "RE_PLAN_SOACHA",
            "Resolución de Sanción G02", true), //
    RESOLUCION_SANCION_H01(115, "/c2/comparendo/resoluciones/sancion/h01", "RE_PLAN_SOACHA",
            "Resolución de Sanción H01", true), //
    RESOLUCION_SANCION_H02(116, "/c2/comparendo/resoluciones/sancion/h02", "RE_PLAN_SOACHA",
            "Resolución de Sanción H02", true), //
    RESOLUCION_SANCION_H03(117, "/c2/comparendo/resoluciones/sancion/h03", "RE_PLAN_SOACHA",
            "Resolución de Sanción H03", true), //
    RESOLUCION_SANCION_H04(118, "/c2/comparendo/resoluciones/sancion/h04", "RE_PLAN_SOACHA",
            "Resolución de Sanción H04", true), //
    RESOLUCION_SANCION_H05(119, "/c2/comparendo/resoluciones/sancion/h05", "RE_PLAN_SOACHA",
            "Resolución de Sanción H05", true), //
    RESOLUCION_SANCION_H06(120, "/c2/comparendo/resoluciones/sancion/h06", "RE_PLAN_SOACHA",
            "Resolución de Sanción H06", true), //
    RESOLUCION_SANCION_H07(121, "/c2/comparendo/resoluciones/sancion/h07", "RE_PLAN_SOACHA",
            "Resolución de Sanción H07", true), //
    RESOLUCION_SANCION_H08(122, "/c2/comparendo/resoluciones/sancion/h08", "RE_PLAN_SOACHA",
            "Resolución de Sanción H08", true), //
    RESOLUCION_SANCION_H09(123, "/c2/comparendo/resoluciones/sancion/h09", "RE_PLAN_SOACHA",
            "Resolución de Sanción H09", true), //
    RESOLUCION_SANCION_H10(124, "/c2/comparendo/resoluciones/sancion/h10", "RE_PLAN_SOACHA",
            "Resolución de Sanción H10", true), //
    RESOLUCION_SANCION_H11(125, "/c2/comparendo/resoluciones/sancion/h11", "RE_PLAN_SOACHA",
            "Resolución de Sanción H11", true), //
    RESOLUCION_SANCION_H12(126, "/c2/comparendo/resoluciones/sancion/h12", "RE_PLAN_SOACHA",
            "Resolución de Sanción H12", true), //
    RESOLUCION_SANCION_H13(127, "/c2/comparendo/resoluciones/sancion/h13", "RE_PLAN_SOACHA",
            "Resolución de Sanción H13", true), //
    RESOLUCION_SANCION_I01(128, "/c2/comparendo/resoluciones/sancion/j01", "RE_PLAN_SOACHA",
            "Resolución de Sanción I01", true), //
    RESOLUCION_SANCION_I02(129, "/c2/comparendo/resoluciones/sancion/j02", "RE_PLAN_SOACHA",
            "Resolución de Sanción I02", true), //
    RESOLUCION_SANCION_J01(130, "/c2/comparendo/resoluciones/sancion/j01", "RE_PLAN_SOACHA",
            "Resolución de Sanción J01", true), //
    RESOLUCION_SANCION_J02(131, "/c2/comparendo/resoluciones/sancion/j02", "RE_PLAN_SOACHA",
            "Resolución de Sanción J02", true), //
    RESOLUCION_SANCION_J03(132, "/c2/comparendo/resoluciones/sancion/j03", "RE_PLAN_SOACHA",
            "Resolución de Sanción J03", true), //
    RESOLUCION_SANCION_J04(133, "/c2/comparendo/resoluciones/sancion/j04", "RE_PLAN_SOACHA",
            "Resolución de Sanción J04", true), //
    RESOLUCION_SANCION_J05(134, "/c2/comparendo/resoluciones/sancion/j05", "RE_PLAN_SOACHA",
            "Resolución de Sanción J05", true), //
    RESOLUCION_SANCION_J06(135, "/c2/comparendo/resoluciones/sancion/j06", "RE_PLAN_SOACHA",
            "Resolución de Sanción J06", true), //
    // TODO definir documento de plantilla en analisis (2016-02-02)
    RESOLUCION_RECTIFICACION(135, "/c2/%s/Generacion_Resolucion_Rectificacion", "RESOLUCION_RECTIFICACION",
            "Al rectificar un comparendo siempre se genera un documento legal que soporte esta rectificacion. Ese documento es la Resolucion de Rectificacion",
            false),
    // TODO definir documento de plantilla en analisis (2016-02-02)
    RESOLUCION_RECTIFICACION_ANULANDO_SANCION(136, "/c2/%s/Generacion_Resolucion_Rectificacion",
            "RESO_RECTIFICA_ANULANDO_SANCION",
            "Al rectificar un comparendo siempre se genera un documento legal que soporte esta rectificacion. Ese documento es la Resolucion de Rectificacion",
            false), //
    APERTURA_IMPUGNACION(137, "/c2/%s/Proceso_Impugnacion-%s", "APERTURA_IMPUGNACION",
            "Al realizar la apertura de un proceso de impugnación se genera el documento de apertura de impugnación",
            false), //
    REALIZAR_FALLO(138, "/c2/%s/Proceso_Impugnacion-%s", "FALLO_IMPUGNACION",
            "Al realizar la aprobacion del fallo de la impugnación se genera un documento del fallo aprobado", false), //
    ACTUALIZAR_UBICABILIDAD(139, "/c2/%s/Ubicabilidad/Autorizaciones_Actualizacion", "ACT_UBICABILIDAD",
            "Al realizar la actualización de datos del participante se genera un documento donde autoriza que las notificaciones se realicen a estos datos",
            false), //
    ACT_PERSONA_JUR(140, "/c2/%s/Ubicabilidad/Autorizaciones_Actualizacion", "ACT_PERSONA_JUR",
            "Al realizar la actualización de datos de la persona juridica se genera un documento donde autoriza que las notificaciones se realicen a estos datos",
            false), //
    ACUERDO_PAGO(141, "/c2/%s", "ACUERDO_PAGO", "Documento se genera con la información del cuadro de amortización",
            false), //
    VOLANTE_PAGO(142, "/c2/%s/Volates_Pago", "VOLANTE_PAGO",
            "Documento se genera con la información del volante de pago seleccionado", false), //
    SOLICITUD_PRUEBAS(143, "/c2/%s/Pruebas", "MEMO_SOLICIT_PRUEBAS", "Documento se genera con solicitud de pruebas",
            false), //
    CIERRE_PRUEBAS(144, "/c2/%s/Pruebas", "CIERRE_PRUEBAS   ", "Documento se genera con el cierre de pruebas", false), //
    IMPULSO_PRUEBAS(145, "/c2/%s/Pruebas/Impulso", "IMPULSO_PRU_DESCRIP",
            "Documento se genera con el impulso de pruebas", false), //
    NOTIFICACION_ANULACION(146, "/c2/%s/Notificación de comparendos", "ANULACION_COM",
            "Documento generado con la anulación de comparendos", false), //
    PONER_FIRME_FINANCIACION(147, "/c2/%s", "CONVENIO_MULTA", "Documento generado al poner en firme una financiación",
            false), //
    CUADRO_AMORTIZACION(148, "/c2/%s", "AMORTIZACION", "Documento generado al solicitar el cuadro de amortización",
            false), //
    AUTO_PAGO(149, "/c2/%s/Auto_Pago", "AUTO_PAGO", "Documento generado al ingresar a coactivo", false), //
    NOTI_ELECTRONICA(150, "/c2/%s/Enotifica", "NOTI_ELECTRONICA", "Documento generado para la notificacion electronica",
            false), //
    ACTA_DE_POSESION(152, "/c2/%s/Acta_Posesion", "ACTA_DE_POSESION", "Documento generado al ingresar a coactivo",
            false), //
    NOTIFICACION_COACTIVO(153, "/c2/%s/Notificacion_Coactivo", "NOTIFICACION_COAC",
            "Documento generado de Notificación coactivo", false), //
    APERTURA_IMPUGNACION_SIN_TERCERO(154, "/c2/%s/Proceso_Impugnacion-%s", "APERT_IMPUG_SIN_TERC",
            "Al realizar la apertura de un proceso de impugnación se genera el documento de apertura de impugnación",
            false), //
    OFICIO_SOLICITUD_PRUEBAS(155, "/c2/%s/Pruebas", "OFICIO_SOLIC_PRUEBAS",
            "Documento se genera con solicitud de pruebas para otras instituciones", false), //
    AUTO_PAGO_SIN_DIRECCION(156, "/c2/%s/Auto_Pago", "APERT_COA_SIN_DIRECC",
            "Documento generado al ingresar a coactivo sin direccion", false), //
    SOLICITUD_OFICIO_BIEN(157, "/c2/%s/Solicitud_oficio_bien", "OFICIO_BANCO_CARTA",
            "Generacion de solicitud de oficio bien", false), //
    ;
    // Existen mas valores en la tabla tipo_documento_generado que se deben colocar por demanda

    private int value;
    private String ubicacion;
    private String codigoPlantilla;
    private String descripcion;
    private boolean resolucionSancion;

    EnumTipoDocumentoGenerado(int value, String ubicacion, String codigoPlantilla, String descripcion,
            boolean resolucionSancion) {
        this.value = value;
        this.ubicacion = ubicacion;
        this.codigoPlantilla = codigoPlantilla;
        this.descripcion = descripcion;
        this.resolucionSancion = resolucionSancion;
    }

    @Override
    public Integer getValue() {
        return value;
    }

    public String getUbicacion() {
        return ubicacion;
    }

    public String getCodigoPlantilla() {
        return codigoPlantilla;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public boolean isResolucionSancion() {
        return resolucionSancion;
    }

    /**
     * Obtiene la enumeracion por su valor
     * 
     * @param value
     * @return
     * @author julio.pinzon 2016-06-15
     */
    public static EnumTipoDocumentoGenerado obtenerPorValor(Integer value) {
        EnumTipoDocumentoGenerado tipo = null;
        for (EnumTipoDocumentoGenerado tipoDocumento : EnumTipoDocumentoGenerado.values()) {
            if (tipoDocumento.getValue().equals(value)) {
                tipo = tipoDocumento;
                break;
            }
        }
        return tipo;
    }

    /**
     * Obtiene la enumeracion por su codigo
     * 
     * @param value
     * @return
     * @author julio.pinzon 2016-06-15
     */
    public static EnumTipoDocumentoGenerado obtenerPorCodigo(String codigo) {
        EnumTipoDocumentoGenerado tipo = null;
        for (EnumTipoDocumentoGenerado tipoDocumento : EnumTipoDocumentoGenerado.values()) {
            if (tipoDocumento.getCodigoPlantilla().equals(codigo)) {
                tipo = tipoDocumento;
                break;
            }
        }
        return tipo;
    }

}
