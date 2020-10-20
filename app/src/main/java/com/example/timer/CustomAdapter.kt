package com.example.timer

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.RelativeLayout
import android.widget.TextView
import androidx.appcompat.view.menu.MenuView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.mikhaellopez.circularimageview.CircularImageView
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.recycler_item.view.*


class CustomAdapter(val dataList: ArrayList<MyItem>) : RecyclerView.Adapter<CustomAdapter.ViewHolder>() {

    public var flag=false
    private var pos =-1
    private lateinit var temp:RelativeLayout

    //this method is returning the view for each item in the list


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CustomAdapter.ViewHolder {
        val v = LayoutInflater.from(parent.context).inflate(R.layout.recycler_item, parent, false)
        return ViewHolder(v)
    }

    //this method is binding the data on the list
    override fun onBindViewHolder(holder: CustomAdapter.ViewHolder, position: Int) {
        val cr = dataList[position]
        holder.name.text = cr.name
        holder.author.text = cr.author
        holder.description.text = cr.description
        holder.language.text = cr.language
        holder.stars.text = cr.stars.toString()
        holder.forks.text = cr.forks.toString()
        holder.url = cr.avatar
        Picasso.get().load(holder.url).into(holder.avatar)
        holder.expandablelayout.visibility=View.GONE



        holder.layout1.setOnClickListener {
            if (pos ==-1) {
                holder.expandablelayout.visibility = View.VISIBLE
                pos = position
                temp = holder.expandablelayout
            }
            else {
                if (pos == position) {
                    holder.expandablelayout.visibility = View.GONE
                    pos =-1
                }
                else {
                    holder.expandablelayout.visibility=View.VISIBLE
                    temp.visibility=View.GONE
                    temp=holder.expandablelayout
                    pos=position
                }
            }
            flag=!(flag)
        }
    }


    //this method is giving the size of the list
    override fun getItemCount(): Int {
        return dataList.size
    }


    //the class is hodling the list view
   inner class ViewHolder(item: View) : RecyclerView.ViewHolder(item) {
        lateinit var name: TextView
        lateinit var author: TextView
        lateinit var avatar: CircularImageView
        lateinit var url: String
        lateinit var description: TextView
        lateinit var language: TextView
        lateinit var stars: TextView
        lateinit var forks: TextView
        lateinit var expandablelayout: RelativeLayout
        lateinit var layout1: RelativeLayout
        lateinit var cardv: CardView

        init {
            name = itemView.text1
            author = itemView.text2
            avatar = itemView.profile_image
            description = itemView.content
            language = itemView.language
            stars = itemView.starNum
            forks = itemView.forkNum
            expandablelayout = itemView.layout2
            layout1 = itemView.layout1
            cardv = itemView.carView

           if(flag==true)
           {
               author.setPadding(0,4,32,8)
           }
            else
           {
               author.setPadding(0,4,32,16)
           }
        }

        //     fun bind(user: MyItem) {
//            itemView.profile_image.setCircleBackgroundColorResource(user.profile_image)
//            itemView.text1.setText(user.text1)
//            itemView.text2.setText(user.text2)
//
//        }
    }
}

