import { Prop, Schema, SchemaFactory } from '@nestjs/mongoose';
import { HydratedDocument } from 'mongoose';

export type UserDocument = HydratedDocument<User>;

@Schema()
export class User {
    @Prop()
    id: string;

    @Prop()
    email: string;

    @Prop()
    username: string;

    @Prop()
    password: string;

    @Prop()
    createdAt: Date;

    @Prop()
    updatedAt: Date;

    @Prop()
    deleted: boolean;
}

export const UserSchema = SchemaFactory.createForClass(User);