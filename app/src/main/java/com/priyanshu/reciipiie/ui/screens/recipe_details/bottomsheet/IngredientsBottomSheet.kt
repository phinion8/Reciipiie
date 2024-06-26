package com.priyanshu.reciipiie.ui.screens.recipe_details.bottomsheet

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.BottomSheetDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.priyanshu.reciipiie.domain.models.AnalyzedInstruction
import com.priyanshu.reciipiie.domain.models.Ingredient
import com.priyanshu.reciipiie.domain.models.Step
import com.priyanshu.reciipiie.ui.components.CustomElevatedButton

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun IngredientsBottomSheet(
    onDismiss: () -> Unit,
    analyzedInstructions: List<AnalyzedInstruction>,
    instruction: String,
    id: String
) {
    val modalBottomSheetState = rememberModalBottomSheetState()
    val ingredientList: ArrayList<Ingredient> = ArrayList()
    var showInstructionBottomSheet by remember {
        mutableStateOf(false)
    }

    if (showInstructionBottomSheet){
        InstructionBottomSheet(onDismiss = {
            showInstructionBottomSheet = false
        }, instruction = instruction, id)
    }

    ModalBottomSheet(
        onDismissRequest = {
            onDismiss()
        },
        sheetState = modalBottomSheetState,
        dragHandle = { BottomSheetDefaults.DragHandle() },
    ) {

        analyzedInstructions.map { analyzedInstruction ->
            analyzedInstruction.steps.map { step: Step ->
                ingredientList.addAll(step.ingredients)
            }
        }

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            IngredientsAccordion(ingredientList = ingredientList)

            Spacer(modifier = Modifier.height(8.dp))

            CustomElevatedButton(onClick = {
                showInstructionBottomSheet = true
            }, text = "Get Instructions")

            Spacer(modifier = Modifier.height(56.dp))
        }
    }
}