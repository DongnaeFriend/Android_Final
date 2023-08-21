package com.example.dongnaefriend_android

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import com.example.dongnaefriend_android.databinding.ActivityAccountbookBinding

class AccountbookActivity : AppCompatActivity(){


    private lateinit var viewBinding : ActivityAccountbookBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)//
        viewBinding = ActivityAccountbookBinding.inflate(layoutInflater)
        setContentView(viewBinding.root)

        //액티비티 실행시 표시할 프래그먼트 체크(가곅부 공유 글쓰기 기능 관련)
        val fragmentCheck = intent.getIntExtra("text", 0)

        if (fragmentCheck == 2){

            viewBinding.btnMyaccount.visibility = View.INVISIBLE
            viewBinding.btnMyaccountUnClicked.visibility = View.VISIBLE
            viewBinding.btnAccountshare.visibility = View.INVISIBLE
            viewBinding.btnAccountshareClicked.visibility = View.VISIBLE

            supportFragmentManager
                .beginTransaction()
                .replace(viewBinding.containerAccount.id, AccountshareFragment())
                .commitAllowingStateLoss()
        }




        else {
            supportFragmentManager
                .beginTransaction()
                .replace(viewBinding.containerAccount.id, MyaccountFragment())
                .commitAllowingStateLoss()
        }

        viewBinding.btnMyaccount.setOnClickListener {
            supportFragmentManager
                .beginTransaction()
                .replace(viewBinding.containerAccount.id, MyaccountFragment())
                .commitAllowingStateLoss()

            viewBinding.btnMyaccount.visibility = View.VISIBLE
            viewBinding.btnMyaccountUnClicked.visibility = View.INVISIBLE
        }

        viewBinding.btnAccountshare.setOnClickListener {
            supportFragmentManager
                .beginTransaction()
                .replace(viewBinding.containerAccount.id, AccountshareFragment())
                .commitAllowingStateLoss()

            viewBinding.btnAccountshareClicked.visibility = View.VISIBLE
            viewBinding.btnMyaccount.visibility = View.INVISIBLE
            viewBinding.btnAccountshare.visibility = View.INVISIBLE
            viewBinding.btnMyaccountUnClicked.visibility = View.VISIBLE


        }

        viewBinding.btnMyaccountUnClicked.setOnClickListener {
            supportFragmentManager
                .beginTransaction()
                .replace(viewBinding.containerAccount.id, MyaccountFragment())
                .commitAllowingStateLoss()

            viewBinding.btnMyaccount.visibility = View.VISIBLE
            viewBinding.btnAccountshare.visibility = View.VISIBLE
            viewBinding.btnMyaccountUnClicked.visibility = View.INVISIBLE
            viewBinding.btnAccountshareClicked.visibility = View.INVISIBLE
        }

        viewBinding.btnAccountshareClicked.setOnClickListener {
            supportFragmentManager
                .beginTransaction()
                .replace(viewBinding.containerAccount.id, AccountshareFragment())
                .commitAllowingStateLoss()

            viewBinding.btnAccountshareClicked.visibility = View.VISIBLE
            viewBinding.btnAccountshare.visibility = View.INVISIBLE


        }

        //navBottom의 menu를 클릭하면, HomeActivity로 이동하며 각 버튼에 맞는 변수 값을 전달함
        viewBinding.navBottom.run{
            setOnItemSelectedListener {
                when(it.itemId){
                    R.id.menu_home ->{
                        val intent = Intent(context, HomeActivity::class.java)


                        context.startActivity(intent)


                    }

                    R.id.menu_dongnaeinformation ->{
                        val intent = Intent(context, HomeActivity::class.java)

                        intent.putExtra("text",3)
                        context.startActivity(intent)


                    }
                    R.id.menu_accountbook ->{



                    }
                    R.id.menu_mypage ->{
                        val intent = Intent(context, HomeActivity::class.java)

                        intent.putExtra("text",5)
                        context.startActivity(intent)


                    }
                }
                true
            }
            selectedItemId = R.id.menu_accountbook



        }

        setSupportActionBar(viewBinding.toolbarAccountbook)
        supportActionBar?.setDisplayShowTitleEnabled(false)


    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_accountplus, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item?.itemId){

            //메모 추가
            R.id.menu_accountmemoplus ->{



            MemoDialog().show(supportFragmentManager, " ")





                return super.onOptionsItemSelected(item)
            }
            R.id.menu_incomeplus ->{

                val intent = Intent(this, IncomePlusActivity::class.java)

                startActivity(intent)



                return super.onOptionsItemSelected(item)
            }


            R.id.menu_paymentplus -> {

                    val intent = Intent(this, PaymentPlus1Activity::class.java)

                    startActivity(intent)


            return super.onOptionsItemSelected(item)
            }
            R.id.menu_setbudget ->{

                val intent = Intent(this, SetBudgetActivity::class.java)

                startActivity(intent)


                return super.onOptionsItemSelected(item)
            }
            else -> return super.onOptionsItemSelected(item)

        }



    }
    fun containerFragmentGone(){
        viewBinding.toolbarAccountbook.visibility = View.GONE
        viewBinding.textviewAccounttitle.visibility = View.GONE
        viewBinding.containerAccount.visibility = View.GONE
        viewBinding.btnAccountshareClicked.visibility = View.GONE
        viewBinding.btnMyaccountUnClicked.visibility = View.GONE
    }

    fun ForAccountDetailGone(){
        viewBinding.toolbarAccountbook.visibility = View.GONE
        viewBinding.textviewAccounttitle.visibility = View.GONE
        viewBinding.containerAccount.visibility = View.GONE
        viewBinding.btnAccountshareClicked.visibility = View.GONE
        viewBinding.btnMyaccountUnClicked.visibility = View.GONE
        viewBinding.btnAccountshare.visibility = View.GONE
        viewBinding.btnMyaccount.visibility = View.GONE
    }





}
