package com.example.myapplication

import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import android.widget.Toolbar
import androidx.core.view.GravityCompat
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar)
        toolbar.setNavigationOnClickListener{
            drawerlayout.openDrawer(GravityCompat.START)
        }

        toolbar.inflateMenu(R.menu.toolbar)

        toolbar.setOnMenuItemClickListener { it ->
            when (it.itemId) {
                R.id.backup -> {
                    Toast.makeText(this, "backup", Toast.LENGTH_SHORT).show()
                    true
                }
                R.id.setting ->{
                    Toast.makeText(this, "setting", Toast.LENGTH_SHORT).show()
                    true
                }
                else -> false
            }
        }

        navigationview.setNavigationItemSelectedListener { it ->
            drawerlayout.closeDrawers()
            true
        }

        floatingbutton.setOnClickListener {
            Snackbar.make(it,"floating Button",Snackbar.LENGTH_SHORT)
                .setAction("确定",{
                    Toast.makeText(this,"确定了",Toast.LENGTH_SHORT).show()
                })
                .show()
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.toolbar,menu)
        return true
    }


    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId){
            android.R.id.home->{
                drawerlayout.openDrawer(GravityCompat.START)
            }
            R.id.backup->{
                Toast.makeText(this,"heihei",Toast.LENGTH_SHORT)
            }
        }
        return super.onOptionsItemSelected(item)
    }


}
