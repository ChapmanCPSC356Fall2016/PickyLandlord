package edu.chapman.cpsc356.pickylandlord.models;

public class CrimeModel
{
    private String text;
    private boolean solved = false;


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
}
