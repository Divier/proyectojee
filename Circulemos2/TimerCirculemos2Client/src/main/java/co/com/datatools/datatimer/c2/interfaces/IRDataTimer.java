package co.com.datatools.datatimer.c2.interfaces;

import javax.ejb.Remote;

import co.com.datatools.datatimer.interfaces.IDataTimer;

/**
 * Indica cual es la interfaz remota la cual hereda de IDataTimer
 * 
 * @author alexander.castro
 *
 */
@Remote
public interface IRDataTimer extends IDataTimer {

}
