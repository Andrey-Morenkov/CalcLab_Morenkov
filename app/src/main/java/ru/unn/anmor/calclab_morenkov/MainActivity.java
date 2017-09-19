package ru.unn.anmor.calclab_morenkov;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity
{
    // Calc's operations
    private final String OPERATION_DUMMY    = "";
    private final String OPERATION_PLUS     = "+";
    private final String OPERATION_MINUS    = "-";
    private final String OPERATION_MULTIPLY = "*";
    private final String OPERATION_DIVIDE   = "/";

    private final String EXECUTE = "=";

    // Memory buttons labels
    private final String MEMORY_PLUS    = "M+";
    private final String MEMORY_MINUS   = "M-";
    private final String MEMORY_READ    = "MR";
    private final String MEMORY_CLEAR   = "MC";
    private final String EMPTY_STRING   = "";

    private final String NO_ERRORS      = "No errors";
    private final String ERR_INPUT      = "Incorrect number";

    // Fields and flags
    private double   mMemoryCell        = 0.0; // calc's memory
    private double   mInternalStorage   = 0.0;
    private String   mCurrentOperation  = OPERATION_DUMMY;

    private EditText tInputField        = null;
    private TextView tMemoryScreen      = null;
    private TextView tErrorLog          = null;

    private void UpdateMemoryValue(double _val)
    {
        mMemoryCell = _val;
        tMemoryScreen.setText(String.valueOf(mMemoryCell));
    }

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button bExecute     = (Button) findViewById(R.id.buttonExecute);
        Button bMemoryPlus  = (Button) findViewById(R.id.buttonMplus);
        Button bMemoryMinus = (Button) findViewById(R.id.buttonMminus);
        Button bMemoryClr   = (Button) findViewById(R.id.buttonMC);
        Button bMemoryRead  = (Button) findViewById(R.id.buttonMR);

        Button bPlus        = (Button) findViewById(R.id.buttonPlus);
        Button bMinus       = (Button) findViewById(R.id.buttonMinus);
        Button bMultiply    = (Button) findViewById(R.id.buttonMultiply);
        Button bDivide      = (Button) findViewById(R.id.buttonDivide);

        tInputField= (EditText) findViewById(R.id.etNumberField);
        tMemoryScreen= (TextView) findViewById(R.id.tMemoryValue);
        tErrorLog = (TextView) findViewById(R.id.ErrorsField);

        // Set the activity fields by default values
        mInternalStorage = 0.0;
        UpdateMemoryValue(0.0);

        bPlus.setText(OPERATION_PLUS);
        bMinus.setText(OPERATION_MINUS);
        bMultiply.setText(OPERATION_MULTIPLY);
        bDivide.setText(OPERATION_DIVIDE);
        bExecute.setText(EXECUTE);

        bMemoryPlus.setText(MEMORY_PLUS);
        bMemoryMinus.setText(MEMORY_MINUS);
        bMemoryClr.setText(MEMORY_CLEAR);
        bMemoryRead.setText(MEMORY_READ);

        tInputField.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                if (!mCurrentOperation.equals(OPERATION_DUMMY))
                {
                    tInputField.setText(EMPTY_STRING); // Clear input field
                }
            }
        });

        // -------------- Buttons section --------------

        bPlus.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                try
                {
                    mCurrentOperation = OPERATION_PLUS;
                    mInternalStorage = Double.parseDouble(tInputField.getText().toString());
                    tErrorLog.setText(NO_ERRORS);
                }
                catch (NumberFormatException e)
                {
                    tErrorLog.setText(ERR_INPUT);
                }
            }
        });

        bMinus.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                try
                {
                    mCurrentOperation = OPERATION_MINUS;
                    mInternalStorage = Double.parseDouble(tInputField.getText().toString());
                    tErrorLog.setText(NO_ERRORS);
                }
                catch (NumberFormatException e)
                {
                    tErrorLog.setText(ERR_INPUT);
                }
            }
        });

        bMultiply.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                try
                {
                    mCurrentOperation = OPERATION_MULTIPLY;
                    mInternalStorage = Double.parseDouble(tInputField.getText().toString());
                    tErrorLog.setText(NO_ERRORS);
                }
                catch (NumberFormatException e)
                {
                    tErrorLog.setText(ERR_INPUT);
                }
            }
        });

        bDivide.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                try
                {
                    mCurrentOperation = OPERATION_DIVIDE;
                    mInternalStorage = Double.parseDouble(tInputField.getText().toString());
                    tErrorLog.setText(NO_ERRORS);
                }
                catch (NumberFormatException e)
                {
                    tErrorLog.setText(ERR_INPUT);
                }
            }
        });

        // -------------- Memory section --------------

        bMemoryRead.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                tInputField.setText(String.valueOf(mMemoryCell));
            }
        });

        bMemoryClr.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                UpdateMemoryValue(0.0);
            }
        });

        bMemoryPlus.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                try
                {
                    UpdateMemoryValue(mMemoryCell + Double.valueOf(tInputField.getText().toString()));
                }
                catch (NumberFormatException e)
                {
                    tErrorLog.setText(ERR_INPUT);
                }
            }
        });

        bMemoryMinus.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                try
                {
                    UpdateMemoryValue(mMemoryCell - Double.valueOf(tInputField.getText().toString()));
                }
                catch (NumberFormatException e)
                {
                    tErrorLog.setText(ERR_INPUT);
                }
            }
        });

        bExecute.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                double tmpRes;
                try
                {
                    tmpRes = Double.valueOf(tInputField.getText().toString());

                    switch (mCurrentOperation)
                    {
                        case OPERATION_PLUS:
                        {
                            // mInternalStorage store previous number
                            mInternalStorage += tmpRes;
                            break;
                        }

                        case OPERATION_MINUS:
                        {
                            mInternalStorage -= tmpRes;
                            break;
                        }

                        case OPERATION_MULTIPLY:
                        {
                            mInternalStorage *= tmpRes;
                            break;
                        }

                        case OPERATION_DIVIDE:
                        {
                            mInternalStorage /= tmpRes;
                            break;
                        }

                        default :
                        {
                            // No operation was selected => nothing to do
                            mInternalStorage = tmpRes;
                        }
                    }

                    tInputField.setText(String.valueOf(mInternalStorage));

                    mInternalStorage = 0.0;
                    mCurrentOperation = OPERATION_DUMMY;
                }
                catch (NumberFormatException e)
                {
                    tErrorLog.setText(ERR_INPUT);
                }
            }
        });
    }
}
