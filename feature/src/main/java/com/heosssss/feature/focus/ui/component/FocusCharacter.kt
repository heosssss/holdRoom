package com.heosssss.feature.focus.ui.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.heosssss.core_ui.R

@Composable
fun FocusCharacter(){
    Surface(
        modifier = Modifier
            .size(220.dp),
        shape = CircleShape,
        color = Color(0xFFB2CCFF),
        shadowElevation = 12.dp
    ) {
        Image(
            painter = painterResource(id = R.drawable.jelly_splash),
            contentDescription = null,
            modifier = Modifier.fillMaxSize(),
            contentScale = ContentScale.Crop
        )
    }
}