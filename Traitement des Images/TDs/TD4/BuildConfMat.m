function [CM,err]=BuildConfMat(lab_true,lab_pred)

Class=unique(lab_true);
C=length(Class);

for c=1:C
    index=find(lab_true==Class(c));
    for k=1:C
        
        CM(c,k)=sum(lab_pred(index)==Class(k));
    end
end

N=length(lab_true);
A=sum(diag(CM));

err=(N-A)/N;