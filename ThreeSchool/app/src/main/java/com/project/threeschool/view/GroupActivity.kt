package com.project.threeschool.view

import android.app.AlertDialog
import android.content.DialogInterface
import android.net.Uri
import android.telecom.Call
import android.view.View
import android.widget.Button
import android.widget.EditText
import androidx.lifecycle.Observer
import com.project.simplecode.spaToastShort
import com.project.threeschool.R
import com.project.threeschool.base.BaseActivity
import com.project.threeschool.databinding.ActivityGroupBinding
import com.project.threeschool.network.RetrofitClient
import com.project.threeschool.viewmodel.GroupViewModel
import kotlinx.android.synthetic.main.activity_group.*
import org.apache.poi.hssf.usermodel.HSSFWorkbook
import org.apache.poi.ss.usermodel.Cell
import org.apache.poi.ss.usermodel.Row
import org.apache.poi.ss.usermodel.Sheet
import org.apache.poi.ss.usermodel.Workbook
import java.io.File
import java.io.FileOutputStream


class GroupActivity : BaseActivity<ActivityGroupBinding, GroupViewModel>() {
    override val viewModel: GroupViewModel = GroupViewModel()

    override val layoutRes: Int
        get() = R.layout.activity_group

    override fun init() {
        with(viewModel){
            retrofit = RetrofitClient.getInstance()
            API = RetrofitClient.getService()
            callList()
        }
    }

    override fun observerViewModel() {
        with(viewModel){

            importExcelBtn.observe(this@GroupActivity, Observer {
                dialog()
            })

            refreshBtn.observe(this@GroupActivity, Observer {
                callList()
            })

        }

    }

    private fun saveExcel() {
        var workbook: Workbook = HSSFWorkbook()
        var sheet: Sheet = workbook.createSheet()
        var row: Row = sheet.createRow(0)
        var cell: Cell

        cell = row.createCell(0)
        cell.setCellValue(viewModel.grade.value)

        row = sheet.createRow(1)

        cell = row.createCell(0)
        cell.setCellValue("번호")

        cell = row.createCell(1)
        cell.setCellValue("온도")

        cell = row.createCell(2)
        cell.setCellValue("정상/비정상")

        for (i in 0 until viewModel.list.size) {
            row = sheet.createRow(i + 2)
            cell = row.createCell(0)
            cell.setCellValue((i + 1).toDouble())
            cell = row.createCell(1)
            cell.setCellValue(viewModel.list[i])
            cell = row.createCell(2)
            if (viewModel.list[i] > 37.4 || viewModel.list[i] < 34) {
                cell.setCellValue("비정상")
            } else {
                cell.setCellValue("정상")
            }
        }
        val xlsFile = File(getExternalFilesDir(null), viewModel.fileName.value!!)
        try {
            val os = FileOutputStream(xlsFile)
            workbook.write(os) // 외부 저장소에 엑셀 파일 생성
        } catch (e: Exception) {
            e.printStackTrace()
        }
        val path: Uri = Uri.fromFile(xlsFile)
        spaToastShort("${xlsFile.absolutePath} 에 저장되었습니다")
    }

    private fun dialog(){
        val dialogView: View = layoutInflater.inflate(R.layout.dialog_count, null)
        val edit : EditText = dialogView.findViewById(R.id.countEdit)

        val builder: AlertDialog.Builder = AlertDialog.Builder(this)
        builder.setView(dialogView)

        builder.setPositiveButton("입력", DialogInterface.OnClickListener { dialog, pos ->
            if (edit.text.toString().isNullOrBlank()) {
                spaToastShort("파일 이름을 적어주세요.")
            } else {
                viewModel.fileName.value = edit.text.toString() + ".xls"
                saveExcel()
            }
        })

        val alertDialog: AlertDialog = builder.create()
        alertDialog.show()
    }

}