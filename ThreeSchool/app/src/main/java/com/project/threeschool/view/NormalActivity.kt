package com.project.threeschool.view

import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.Observer
import com.project.simplecode.spaToastShort
import com.project.threeschool.R
import com.project.threeschool.adapter.TempListAdapter
import com.project.threeschool.base.BaseActivity
import com.project.threeschool.databinding.ActivityNormalBinding
import com.project.threeschool.viewmodel.NormalViewModel
import kotlinx.android.synthetic.main.activity_normal.*
import org.apache.poi.hssf.usermodel.HSSFWorkbook
import org.apache.poi.ss.usermodel.Cell
import org.apache.poi.ss.usermodel.Row
import org.apache.poi.ss.usermodel.Sheet
import org.apache.poi.ss.usermodel.Workbook
import java.io.File
import java.io.FileOutputStream

class NormalActivity : BaseActivity<ActivityNormalBinding, NormalViewModel>() {
    override val viewModel: NormalViewModel = NormalViewModel()

    override val layoutRes: Int
        get() = R.layout.activity_normal

    var tempListAdapter : TempListAdapter = TempListAdapter(viewModel.originList)

    override fun init() {
        rcViewList.adapter = tempListAdapter
    }

    override fun observerViewModel() {
        with(viewModel){
            importExcelBtn.observe(this@NormalActivity, Observer {
                if(originList.size < 1){
                    spaToastShort("아직까지 값이 없습니다.")
                }
                else{
                    saveExcel()
                }

            })
        }
    }

    fun saveExcel() {
        var workbook: Workbook = HSSFWorkbook()
        var sheet: Sheet = workbook.createSheet()
        var row: Row = sheet.createRow(0)
        var cell: Cell

        cell = row.createCell(0)
        cell.setCellValue("번호")

        cell = row.createCell(1)
        cell.setCellValue("온도")

        cell = row.createCell(2)
        cell.setCellValue("정상/비정상")

        for (i in 0 until viewModel.originList.size) {
            row = sheet.createRow(i + 1)
            cell = row.createCell(0)
            cell.setCellValue((i + 1).toDouble())
            cell = row.createCell(1)
            cell.setCellValue(viewModel.originList[i].temperature)
            cell = row.createCell(2)
            if (viewModel.originList[i].checkTemp) {
                cell.setCellValue("정상")
            } else {
                cell.setCellValue("비정상")
            }
        }
        val xlsFile = File(getExternalFilesDir(null), "checkTemp.xls")
        try {
            val os = FileOutputStream(xlsFile)
            workbook.write(os) // 외부 저장소에 엑셀 파일 생성
        } catch (e: Exception) {
            e.printStackTrace()
        }
        val path: Uri = Uri.fromFile(xlsFile)
        spaToastShort("${xlsFile.absolutePath} 에 저장되었습니다")
//        val shareIntent = Intent(Intent.ACTION_SEND)
//        shareIntent.type = "application/excel"
//        shareIntent.putExtra(Intent.EXTRA_STREAM, path)
//        startActivity(Intent.createChooser(shareIntent, "엑셀 내보내기"))
    }

}