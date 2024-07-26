package com.makeevrserg.empireprojekt.mobile.core.ui.searchbar

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.systemBarsPadding
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.AppBarDefaults
import androidx.compose.material.ContentAlpha
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.material.TextFieldDefaults
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Search
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.unit.dp

@Suppress("LongMethod")
@Composable
fun SearchAppBar(
    query: String,
    onTextChanged: (String) -> Unit,
    onCloseClicked: () -> Unit,
    modifier: Modifier = Modifier,
    actions: @Composable () -> Unit = {},
    focusRequester: FocusRequester = remember { FocusRequester() },
    hint: String = "...",
) {
    val keyboardController = LocalSoftwareKeyboardController.current
    Column {
        Box(modifier = Modifier.systemBarsPadding())
        Surface(
            modifier = modifier
                .fillMaxWidth()
                .height(64.dp),
            elevation = AppBarDefaults.TopAppBarElevation,
            color = MaterialTheme.colors.primaryVariant
        ) {
            LaunchedEffect(Unit) {
                focusRequester.requestFocus()
            }
            TextField(
                value = query,
                onValueChange = onTextChanged,
                modifier = Modifier
                    .fillMaxWidth()
                    .focusRequester(focusRequester),
                placeholder = {
                    Text(
                        modifier = Modifier.alpha(ContentAlpha.medium),
                        text = hint,
                        color = MaterialTheme.colors.onPrimary
                    )
                },
                singleLine = true,
                leadingIcon = {
                    IconButton(
                        onClick = { },
                        modifier = Modifier.alpha(ContentAlpha.medium)
                    ) {
                        Icon(
                            imageVector = Icons.Filled.Search,
                            contentDescription = null,
                            tint = MaterialTheme.colors.onPrimary
                        )
                    }
                },
                trailingIcon = {
                    Row {
                        IconButton(
                            onClick = {
                                focusRequester.freeFocus()
                                keyboardController?.hide()
                                onCloseClicked.invoke()
                            },
                            modifier = Modifier.alpha(ContentAlpha.medium)
                        ) {
                            Icon(
                                imageVector = Icons.Filled.Close,
                                contentDescription = "",
                                tint = MaterialTheme.colors.onPrimary
                            )
                        }
                        actions()
                    }
                },
                keyboardOptions = KeyboardOptions(imeAction = ImeAction.Search),
                keyboardActions = KeyboardActions(onSearch = {
                    focusRequester.freeFocus()
                    keyboardController?.hide()
                }),
                colors = TextFieldDefaults.textFieldColors(
                    backgroundColor = Color.Transparent,
                    cursorColor = MaterialTheme.colors.secondaryVariant,
                    textColor = MaterialTheme.colors.onPrimary,
                    focusedIndicatorColor = MaterialTheme.colors.secondaryVariant
                )
            )
        }
    }
}
