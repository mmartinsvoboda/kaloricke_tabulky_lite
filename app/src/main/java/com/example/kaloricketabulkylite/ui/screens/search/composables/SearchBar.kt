package com.example.kaloricketabulkylite.ui.screens.search.composables

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.material.TextFieldDefaults
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.example.kaloricketabulkylite.R
import com.example.kaloricketabulkylite.ui.theme.KalorickeTabulkyLiteTheme
import kotlinx.coroutines.flow.StateFlow

@Composable
fun SearchBar(
    modifier: Modifier,
    queryStateFlow: StateFlow<String>,
    onQueryChange: (String) -> Unit
) {
    val query = queryStateFlow.collectAsState()

    TextField(
        value = query.value,
        onValueChange = onQueryChange,
        modifier = modifier
            .fillMaxWidth(),
        placeholder = {
            Text(text = stringResource(R.string.to_search))
        },
        leadingIcon = {
            Icon(
                imageVector = Icons.Default.Search,
                contentDescription = null
            )
        },
        maxLines = 1,
        shape = RoundedCornerShape(20.dp),
        colors = TextFieldDefaults.textFieldColors(
            backgroundColor = KalorickeTabulkyLiteTheme.colors.surface,
            focusedIndicatorColor = Color.Transparent,
            unfocusedIndicatorColor = Color.Transparent,
            disabledIndicatorColor = Color.Transparent
        )
    )
}