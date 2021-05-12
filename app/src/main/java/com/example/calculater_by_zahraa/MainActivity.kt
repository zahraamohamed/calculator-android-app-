package com.example.calculater_by_zahraa

import android.content.Intent
import android.content.pm.ActivityInfo
import android.graphics.Color
import android.graphics.drawable.Drawable
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import android.widget.Toast
import com.example.calculater_by_zahraa.databinding.ActivityMainBinding
import net.objecthunter.exp4j.ExpressionBuilder
import java.lang.Exception


class MainActivity : AppCompatActivity() {
    private lateinit var binding:ActivityMainBinding
    private var currentNumber: Double = 0.0 // Value can be changed.
    private lateinit var tempResult:String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        setClickOperationLandScape()
        setClickNumber()
        setClickOperation()
        rotateScreen()
        //open page of length
        binding.ruler?.setOnClickListener {
            val intent = Intent(this,length::class.java)
            startActivity(intent) }

    }
    // set operation of number in txtview
    private fun setClickOperation() {
        binding.plus.setOnClickListener {appendOnClick(false,"+")}
        binding.minus.setOnClickListener {appendOnClick(false,"-")}
        binding.div.setOnClickListener {appendOnClick(false,"/")}
        binding.multiply.setOnClickListener {appendOnClick(false,"*")}
        binding.plusMinus.setOnClickListener {onSign()}
        binding.arcStart.setOnClickListener {appendOnClick(false,"(")}
        binding.arcEnd.setOnClickListener {appendOnClick(false,")")}
        binding.clear.setOnClickListener {clear()}
        binding.btn?.setOnClickListener { clear1() }
        binding.delete?.setOnClickListener { deleteLastChar() }
        binding.equal.setOnClickListener {result()}
    }
    private fun setClickOperationLandScape(){

        binding.e?.setOnClickListener {appendOnClick(false,"e")}
        binding.Log10?.setOnClickListener {appendOnClick(false,"log")}
        binding.ln?.setOnClickListener {appendOnClick(false,"ln")}
        binding.cos?.setOnClickListener {appendOnClick(false,"cos")}
        binding.sin?.setOnClickListener {appendOnClick(false,"sin")}
        binding.tan?.setOnClickListener {appendOnClick(false,"tan")}
        binding.acos?.setOnClickListener {appendOnClick(false,"cos")}
        binding.asin?.setOnClickListener {appendOnClick(false,"asin")}
        binding.atan?.setOnClickListener {appendOnClick(false,"atan")}
        binding.cosh?.setOnClickListener {appendOnClick(false,"acosh")}
        binding.sinh?.setOnClickListener {appendOnClick(false,"sinh")}
        binding.tanh?.setOnClickListener {appendOnClick(false,"tanh")}
        binding.pi?.setOnClickListener {appendOnClick(false,"pi")}
        binding.sqrt?.setOnClickListener {appendOnClick(false,"sqrt")}
        binding.rad?.setOnClickListener {appendOnClick(false,"rad")}//
        binding.x2?.setOnClickListener {appendOnClick(false,"^(2)")}
        binding.x2?.setOnClickListener {appendOnClick(false,"^(3)")}
        binding.Factorial?.setOnClickListener {appendOnClick(false,"!")}//
        binding.percent?.setOnClickListener {appendOnClick(false,"%")}
    }

    // set text of number in txtview
    private fun setClickNumber() {
        binding.zero.setOnClickListener {
            if (binding.operationTxtView.text != "0") {appendOnClick(true,"0")}
        }
        binding.one.setOnClickListener {appendOnClick(true,"1")}
        binding.two.setOnClickListener {appendOnClick(true,"2")}
        binding.three.setOnClickListener {appendOnClick(true,"3")}
        binding.four.setOnClickListener {appendOnClick(true,"4")}
        binding.five.setOnClickListener {appendOnClick(true,"5")}
        binding.six.setOnClickListener {appendOnClick(true,"6")}
        binding.seven.setOnClickListener {appendOnClick(true,"7")}
        binding.eight.setOnClickListener {appendOnClick(true,"8")}
        binding.nine.setOnClickListener {appendOnClick(true,"9")}
        binding.dot.setOnClickListener {appendOnClick(true,".")
        }

    }
    //methods of calculator
    //method ofset txet in txtview
    private fun appendOnClick(clear:Boolean,string:String){

        if(clear){
            txtResult().text=""
            val oldValue=txtView().text.toString()
           txtView().text=oldValue + string


        }
        else{

           txtView().append(txtResult().text)
            txtView().append(string)

            txtResult().text=""

            }
        }
    private fun onSign() {

       tempResult = (txtView().text.toString().toDouble() * -1.0).toString()

      txtView().text=tempResult

    }


//    private fun stringToDbl(input:TextView):Double{
//        return input.text.toString().toDouble()
//    }
private fun clear() {
    txtResult().text=""
    txtView().text=""


}
    private fun clear1() {

     txtHistory()?.text  =""

    }

    private fun deleteLastChar(){
        if (txtView().text.isNotBlank()){
        txtView().text=txtView().text.substring(0,(txtView().text).length - 1)}
        else if (txtView().text.isNotBlank()){//do nothing
         }
    }

    private fun result(){
       try {
           val input= ExpressionBuilder(txtView().text.toString()).build()
           val output=input.evaluate()
           val longOutput=output.toLong()
           if(output==longOutput.toDouble()){
               txtResult().text=longOutput.toString()
           }else{
            txtResult().text=output.toString()

           }

           history()

           txtView().text=""

       }catch (e:Exception){
           Toast.makeText(this, e.message, Toast.LENGTH_LONG).show()
       }
    }
    private fun history(){
        txtHistory()?.setTextColor(Color.WHITE)
        txtHistory()?.append("\n")
        txtHistory()?.append(txtView().text)
        txtHistory()?.append("=")
        txtHistory()?.append(txtResult().text)
        txtHistory()?.append("\n")
        txtHistory()?.append("_____________")
        txtHistory()?.append("\n")

    }


    private fun txtHistory():TextView?{
        return binding.txtHistory
    }
    private fun txtResult():TextView{
       return binding.txtResult
    }
    private fun txtView():TextView{
        return binding.operationTxtView
    }

private fun rotateScreen(){
    binding.rotate?.setOnClickListener { setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE)
    }
    binding.mini?.setOnClickListener { setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT)}
}
    }

