/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package persistence;

import java.io.Serializable;
import java.util.ArrayList;

/**
 *
 * @author morga
 */
public interface IRepository<T, ID extends Serializable> {

    public void toAdd(T entidade);

    public void edit(T entidade);

    public void remove(T entidade);

    public ArrayList<T> search();

    public T search(ID id);
}
