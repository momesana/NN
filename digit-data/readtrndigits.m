fid = fopen('digits.trn','r');

S = fscanf(fid,'%f');

fclose(fid); 

S=reshape(S,192+10,round(size(S,1)/(192+10)))';

S=diag((1./max(S')))*S;

S=S>0.1;

NR=12 % Zeigt exemplarisch die Ziffer Nummer 12 an

image(reshape(S(NR,1:192),12,16)'*64);
title(sprintf('Ziffer Nr %i soll eine %i sein.',NR,find(S(NR,193:202))-1));
axis image off;
