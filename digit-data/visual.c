#include <stdio.h>
#include <stddef.h>

/* DEFINES */
#define  NrINPUTS    192
#define  NrOUTPUTS   10


/* Hauptprogramm */
main() 
{
   FILE           *InFilePtr, *OutFilePtr;
   char           pixel, input_file[20], output_file[20] = "DIGITS.ASC";
   float          input_vector[NrINPUTS], label_vector;
   int            label, i, vec_num = 0;

   printf("Input File:  ");
   scanf("%s", input_file);

   InFilePtr  = fopen(input_file,  "r");
   OutFilePtr = fopen(output_file, "w");

   if(InFilePtr==NULL)
   {
      printf("Error Opening Input File\n");
      return 0;
   }

   while(1)
   {

/* Read Input - Vector */
      for(i=0; i<NrINPUTS; i++)
         if(fscanf(InFilePtr, "%f", &input_vector[i])==EOF)
            return 0;

/* Read Label - Vector and determine the Label */
      label = -1;
      for(i=0; i<NrOUTPUTS; i++)
      {
         if(fscanf(InFilePtr, "%f", &label_vector)==EOF)
            return 0;

         if(label_vector==1.0)
            label = i;
      }

/* Print the Image of the Digit */
      fprintf(OutFilePtr, "---------------------\n");
      fprintf(OutFilePtr, "Bild %4d | Label : %1d\n", vec_num, label);
      fprintf(OutFilePtr, "---------------------\n");
      for(i=0; i<NrINPUTS; i++)
      {
         if(input_vector[i]>0.5)
            pixel = '*';
         else if(input_vector[i]==0.0)
            pixel = ' ';
         else
            pixel = '.';

         fprintf(OutFilePtr, "%c", pixel);
         if((i+1)%12 == 0)
            fprintf(OutFilePtr, "\n");
      }
      fprintf(OutFilePtr, "\n");

      vec_num++;

   }

   return 0;

}
