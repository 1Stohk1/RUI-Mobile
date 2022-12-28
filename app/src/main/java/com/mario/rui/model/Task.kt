package com.mario.rui.model

class Task(var owner: String?,
           var text: String?,
           var status: Int?)
/*
status values:
    0 = not done
    1 = pending
    2 = done
   -1 = impossible to do
*/