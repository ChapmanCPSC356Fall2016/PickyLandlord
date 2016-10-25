package edu.chapman.cpsc356.pickylandlord.models;

import org.joda.time.DateTime;

public class CrimeModel
{
    private String text;
    private DateTime date;
    private boolean solved;

    public CrimeModel()
    {
        this.date = DateTime.now();
        this.solved = false;
    }

    public String getText()
    {
        return text;
    }

    public void setText(String text)
    {
        this.text = text;
    }

    public boolean isSolved()
    {
        return solved;
    }

    public void setSolved(boolean solved)
    {
        this.solved = solved;
    }

    public DateTime getDate()
    {
        return date;
    }
}
