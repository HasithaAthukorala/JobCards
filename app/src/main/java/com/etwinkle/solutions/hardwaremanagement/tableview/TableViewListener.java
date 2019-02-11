/*
 * Copyright (c) 2018. Evren Coşkun
 *
 *  Licensed under the Apache License, Version 2.0 (the "License");
 *  you may not use this file except in compliance with the License.
 *  You may obtain a copy of the License at
 *
 *        http://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing, software
 *  distributed under the License is distributed on an "AS IS" BASIS,
 *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  See the License for the specific language governing permissions and
 *  limitations under the License.
 *
 */

package com.etwinkle.solutions.hardwaremanagement.tableview;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.FragmentActivity;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.EditText;
import android.widget.Toast;

import com.etwinkle.solutions.hardwaremanagement.R;
import com.etwinkle.solutions.hardwaremanagement.barcodereader.QRActivity;
import com.etwinkle.solutions.hardwaremanagement.fragments.MainFragment;
import com.etwinkle.solutions.hardwaremanagement.models.ShopEquipment;
import com.etwinkle.solutions.hardwaremanagement.tableview.holder.ColumnHeaderViewHolder;
import com.etwinkle.solutions.hardwaremanagement.tableview.popup.ColumnHeaderLongPressPopup;
import com.etwinkle.solutions.hardwaremanagement.tableview.popup.RowHeaderLongPressPopup;
import com.etwinkle.solutions.hardwaremanagement.utils.CustomDialog;
import com.etwinkle.solutions.hardwaremanagement.utils.OnDataChangeEvent;
import com.evrencoskun.tableview.TableView;
import com.evrencoskun.tableview.adapter.AbstractTableAdapter;
import com.evrencoskun.tableview.listener.ITableViewListener;

import org.greenrobot.eventbus.EventBus;

import java.util.List;

/**
 * Created by evrencoskun on 21/09/2017.
 */

public class TableViewListener implements ITableViewListener, CustomDialog.NoticeDialogListener {

    private Toast mToast;
    private Context mContext;
    private TableView mTableView;
    private List<ShopEquipment> shopEquipmentsList;
    private static int rowID;

    public TableViewListener(TableView tableView,List<ShopEquipment> shopEquipmentsList) {
        this.shopEquipmentsList = shopEquipmentsList;
        this.mContext = tableView.getContext();
        this.mTableView = tableView;
        rowID = 0;
    }

    /**
     * Called when user click any cell item.
     *
     * @param cellView : Clicked Cell ViewHolder.
     * @param column   : X (Column) position of Clicked Cell item.
     * @param row      : Y (Row) position of Clicked Cell item.
     */
    @Override
    public void onCellClicked(@NonNull RecyclerView.ViewHolder cellView, int column, int row) {

        // Do what you want.
        showToast("Cell " + column + " " + row + " has been clicked.");

    }

    /**
     * Called when user long press any cell item.
     *
     * @param cellView : Long Pressed Cell ViewHolder.
     * @param column   : X (Column) position of Long Pressed Cell item.
     * @param row      : Y (Row) position of Long Pressed Cell item.
     */
    @Override
    public void onCellLongPressed(@NonNull RecyclerView.ViewHolder cellView, final int column,
                                  int row) {
        // Do What you want
        showToast("Cell " + column + " " + row + " has been long pressed.");
    }

    /**
     * Called when user click any column header item.
     *
     * @param columnHeaderView : Clicked Column Header ViewHolder.
     * @param column           : X (Column) position of Clicked Column Header item.
     */
    @Override
    public void onColumnHeaderClicked(@NonNull RecyclerView.ViewHolder columnHeaderView, int
            column) {
        // Do what you want.
        showToast("Column header  " + column + " has been clicked.");
    }

    /**
     * Called when user long press any column header item.
     *
     * @param columnHeaderView : Long Pressed Column Header ViewHolder.
     * @param column           : X (Column) position of Long Pressed Column Header item.
     */
    @Override
    public void onColumnHeaderLongPressed(@NonNull RecyclerView.ViewHolder columnHeaderView, int
            column) {

        if (columnHeaderView != null && columnHeaderView instanceof ColumnHeaderViewHolder) {
            // Create Long Press Popup
            ColumnHeaderLongPressPopup popup = new ColumnHeaderLongPressPopup(
                    (ColumnHeaderViewHolder) columnHeaderView, mTableView);
            // Show
            popup.show();
        }
    }

    /**
     * Called when user click any Row Header item.
     *
     * @param rowHeaderView : Clicked Row Header ViewHolder.
     * @param row           : Y (Row) position of Clicked Row Header item.
     */
    @Override
    public void onRowHeaderClicked(@NonNull RecyclerView.ViewHolder rowHeaderView, int row) {
        // Do what you want.
        showNoticeDialog(shopEquipmentsList,row);
//        showToast("Row header " + row + " has been clicked.");
    }

    /**
     * Called when user long press any row header item.
     *
     * @param rowHeaderView : Long Pressed Row Header ViewHolder.
     * @param row           : Y (Row) position of Long Pressed Row Header item.
     */
    @Override
    public void onRowHeaderLongPressed(@NonNull RecyclerView.ViewHolder rowHeaderView, int row) {

        if (rowHeaderView != null) {
            // Create Long Press Popup
            RowHeaderLongPressPopup popup = new RowHeaderLongPressPopup(rowHeaderView, mTableView);
            // Show
            popup.show();
        }
    }


    private void showToast(String p_strMessage) {
        if (mToast == null) {
            mToast = Toast.makeText(mContext, "", Toast.LENGTH_SHORT);
        }

        mToast.setText(p_strMessage);
        mToast.show();
    }

    @Override
    public void onDialogPositiveClick(DialogFragment dialog) {
        Log.d("here","asa");
        EditText balance = (EditText) dialog.getView().findViewById(R.id.balance);
        EditText sold = (EditText) dialog.getView().findViewById(R.id.sold);
        //EventBus.getDefault().post(new OnDataChangeEvent(shopEquipmentsList,rowID,String.valueOf(balance.getText()),String.valueOf(sold.getText())));
    }

    @Override
    public void onDialogNegativeClick(DialogFragment dialog) {

    }

    public void showNoticeDialog(List<ShopEquipment> shopEquipmentsList,int rowID) {
        // Create an instance of the dialog fragment and show it
        CustomDialog.setShopEquipmentsList(shopEquipmentsList);
        CustomDialog.setRowID(rowID);
        DialogFragment dialog = new CustomDialog();
        dialog.show(((FragmentActivity) mContext).getSupportFragmentManager(), "NoticeDialogFragment");
    }

    public static int getRowID() {
        return rowID;
    }

    public static void setRowID(int rowID) {
        TableViewListener.rowID = rowID;
    }


}
