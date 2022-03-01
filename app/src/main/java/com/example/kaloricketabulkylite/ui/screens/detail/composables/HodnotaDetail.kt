package com.example.kaloricketabulkylite.ui.screens.detail.composables

import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import com.example.kaloricketabulkylite.data.local.entity.potravina.Hodnota
import com.example.kaloricketabulkylite.data.local.entity.potravina.Jednotka
import java.math.RoundingMode
import java.text.DecimalFormat

@Composable
fun HodnotaDetail(
    title: String,
    hodnota: Hodnota?,
    jednotka: Jednotka
) {
    hodnota?.value?.let {
        val df = DecimalFormat("#.###")
        df.roundingMode = RoundingMode.DOWN
        val roundoff = df.format((it.toFloatOrNull() ?: 0f).times(jednotka.nasobek ?: 1))

        Text(
            text = "$title: ${roundoff}${hodnota.jedn}"
        )
    }
}